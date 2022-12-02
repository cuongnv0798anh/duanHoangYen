package com.example.duan_dulich.controller;

import com.example.duan_dulich.model.bo.Respon;
import com.example.duan_dulich.model.bo.ResponSum;
import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.bo.SystemResponse;
import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.Role;
import com.example.duan_dulich.model.in.LoginIn;
import com.example.duan_dulich.model.in.RegisterIn;
import com.example.duan_dulich.repository.AccountRepository;
import com.example.duan_dulich.repository.RoleRepository;
import com.example.duan_dulich.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@CrossOrigin("*")
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository ;
    @Autowired
    private RoleRepository roleRepository ;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterIn registerIn){
        return new ResponseEntity<>(accountService.register(registerIn), HttpStatus.OK);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<SystemResponse<Object>> login(@RequestBody LoginIn loginIn){
        return accountService.login(loginIn);
    }
//    @PostMapping(value ="/verify/agency")
//    public ResponseEntity verifyAgency(@RequestParam Integer id){
//        AccountRepository

//    }
@PostMapping(value ="/verify_agency")
public ResponseEntity verifyAgency(@RequestParam Integer id){
    Account account = accountRepository.getByIdAccount(id) ;
    if (account.isActive() == true)
    {
        return ResponseEntity.ok("Tai khoan da duoc kich hoat");
    }
    account.setIdAccount(id);
    account.setActive(true);
    accountRepository.save(account);
    return ResponseEntity.ok(new ResponseCRUD(null));

}
    @GetMapping("/getAgency")
    ResponseEntity getA() {
        Role role = roleRepository.findByNameRole("ROLE_AGENCY");
        List<Account> list = accountRepository.getAllByRoles(role);
        return ResponseEntity.ok(new ResponseCRUD(list));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/role/{id}")
    ResponSum get(@PathVariable Integer id){
        int getSumAngency = 0;
        int getSumUser=0;
        int getSumAll=0;
        switch (id){
            case 1 :
                getSumUser = accountRepository.getSum(id);
                break;
            case 3 :
                getSumAngency = accountRepository.getSum(id);
                break;
        }
        getSumAll = accountRepository.getSumAll();
        return new ResponSum(0,"Success",getSumUser,getSumAngency,getSumAll);
    }



    @GetMapping(value = "/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("ok test thanh cong", HttpStatus.OK);
    }
}
