package com.example.duan_dulich.thanhtoanhoadon.entity;

import com.example.duan_dulich.model.entity.TourEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // thông tin người mua
    @Column
    private Integer idNguoiMua;
    @Column
    private String tenNguoiDk;
    @Column
    private String sdtNguoiDk;
    @Column
    private String soCMNDNguoiDk;

    // địa điểm đón trả
    @Column
    private String diaDiemDuaDon;

    // thông tin tour
    @Column
    private Integer idTour;

    @Column
    private Integer tongSoTien;

    private Integer status = 1;


}
