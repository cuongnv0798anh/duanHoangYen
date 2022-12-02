package com.example.duan_dulich.service;

import com.example.duan_dulich.model.bo.ResponAuth;
import com.example.duan_dulich.model.bo.SystemResponse;
import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.Role;
import com.example.duan_dulich.model.in.LoginIn;
import com.example.duan_dulich.model.in.RegisterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService extends UserDetailsService {
    ResponAuth register(RegisterIn registerIn);
    ResponseEntity<SystemResponse<Object>> login(LoginIn loginIn);

    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
