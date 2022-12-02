package com.example.duan_dulich.thanhtoanhoadon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "the_atm")
public class AtmThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String maAtm;

    @Column
    private String passAtm;

    @Column
    private Integer soDu = 0;
}
