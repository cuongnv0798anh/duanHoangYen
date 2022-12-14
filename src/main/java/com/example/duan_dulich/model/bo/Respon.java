package com.example.duan_dulich.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respon {
    private Integer errorCode;
    private String errorMessenger;
    private Integer getMoneyTour;
    private Integer getCountTour;

    public Respon(Integer errorCode, String errorMessenger) {
        this.errorCode = errorCode;
        this.errorMessenger = errorMessenger;
    }
}
