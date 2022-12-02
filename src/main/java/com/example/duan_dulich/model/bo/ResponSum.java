package com.example.duan_dulich.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponSum {
    private Integer errorCode;
    private String errorMessenger;
    private Integer getSumUser;
    private Integer getSumAngency;
    private Integer getSumAll;

    public ResponSum(Integer errorCode, String errorMessenger) {
        this.errorCode = errorCode;
        this.errorMessenger = errorMessenger;
    }
}
