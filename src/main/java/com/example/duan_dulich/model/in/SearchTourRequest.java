package com.example.duan_dulich.model.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchTourRequest {

    private Integer idCompany;


    private String diaDiem;

    private String ngay;

    private Integer price;

    private Integer totalDay;
}
