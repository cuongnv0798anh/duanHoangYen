package com.example.duan_dulich.controller;

import com.example.duan_dulich.model.entity.Role;
import com.example.duan_dulich.repository.AccountRepository;
import com.example.duan_dulich.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tool")
public class ToolController {
@Autowired
    AccountRepository accountRepository ;
@Autowired
    RoleRepository roleRepository ;
@PostMapping(value = "create_role")
    public ResponseEntity<?> createRole()
{
    List<Role> role = new ArrayList();
    role.add(new Role("ROLE_USER") );
    role.add(new Role("ROLE_ADMIN"));
    role.add(new Role("ROLE_AGENCY"));
    List<Role> roleSystem = roleRepository.findAll();
    if ( roleSystem.size() == 0  ){
        roleRepository.saveAll(role);
        return ResponseEntity.ok("Them role thanh cong");
    }
    return ResponseEntity.badRequest().body("Role da ton tai") ;
}
}
