package com.example.duan_dulich.model.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterIn {
    private String userName;
    private String fullName;
    private String passWord;
    private String email;
    private String phone;
    private String company;
}
