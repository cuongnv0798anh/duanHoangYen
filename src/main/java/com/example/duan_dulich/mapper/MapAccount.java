package com.example.duan_dulich.mapper;

import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.in.RegisterIn;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class MapAccount {
    public static Account map(RegisterIn registerIn)
    {
        Account accounts = new Account();
        accounts.setEmail(registerIn.getEmail());
        accounts.setUserName(registerIn.getUserName());
        accounts.setFullName(registerIn.getFullName());
        String hash = BCrypt.hashpw(registerIn.getPassWord(),BCrypt.gensalt(10));
        accounts.setPassWord(hash);
        accounts.setPhone(registerIn.getPhone());
        return accounts;
    }
}
