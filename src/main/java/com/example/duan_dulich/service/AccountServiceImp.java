package com.example.duan_dulich.service;

import com.example.duan_dulich.common.StringResponse;
import com.example.duan_dulich.jwt.JwtUtility;
import com.example.duan_dulich.mapper.MapAccount;
import com.example.duan_dulich.model.bo.ResponAuth;
import com.example.duan_dulich.model.bo.Response;
import com.example.duan_dulich.model.bo.SystemResponse;
import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.Role;
import com.example.duan_dulich.model.in.LoginIn;
import com.example.duan_dulich.model.in.RegisterIn;
import com.example.duan_dulich.repository.AccountRepository;
import com.example.duan_dulich.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;


    @Override
    public ResponAuth register(RegisterIn registerIn) {
        Account accounts = MapAccount.map(registerIn);
        Role role ;
        if (accountRepository.existsAccountByUserName(registerIn.getUserName()) == true) {
            return new ResponAuth(false, StringResponse.Regester_Account_Error);
        }
        if(registerIn.getCompany() == null || registerIn.getCompany()== "")
        {
             role = roleRepository.findByNameRole("ROLE_USER");
        }
        else {
            role = roleRepository.findByNameRole("ROLE_AGENCY");
        }
        accounts.setRoles(Collections.singleton(role));
        accountRepository.save(accounts);
        return new ResponAuth(true, StringResponse.Regester_Account);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> login(LoginIn loginIn) {
        Account account = accountRepository.findAccountByUserName(loginIn.getUserName());
        if (account == null) {
            return Response.badRequest(StringResponse.Account_Not_Found);
        }
        if (BCrypt.checkpw(loginIn.getPassWord(), account.getPassWord()) == false) {
            return Response.badRequest(StringResponse.Pass_Wrong);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginIn.getUserName(), loginIn.getPassWord()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String jwt = jwtUtility.generateJwtToken(loginIn.getUserName());
        return Response.ok(jwt, authorities,authentication.getName(),account.getIdAccount());
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account user = accountRepository.findAccountByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        List<Role> userRole = (List<Role>) user.getRoles();
        for (int i = 0; i < userRole.size(); i++) {
            System.out.printf("" + userRole.get(i).getNameRole());
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority( userRole.get(i).getNameRole());
            grantedAuthorityList.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), grantedAuthorityList);
    }
}