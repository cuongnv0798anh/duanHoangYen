package com.example.duan_dulich.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private Integer idUser;
    private Integer idCmt;
    private Integer status;
}
