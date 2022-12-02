package com.example.duan_dulich.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {
    private Integer id;
    private String createBy ;

    private double price;

    private String description;

    private String title;

    private String totalMember;

    private Date timeStart;

    private int totalDay;

    private boolean productStatus;

    private int idCompany;

    private Collection image ;
    private int demTim ;
}
