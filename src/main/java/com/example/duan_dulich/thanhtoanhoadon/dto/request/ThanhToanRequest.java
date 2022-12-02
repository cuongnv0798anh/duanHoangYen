package com.example.duan_dulich.thanhtoanhoadon.dto.request;

import com.example.duan_dulich.model.entity.TourEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ThanhToanRequest {

    // thông tin người đăng ký
    @NotNull
    private Integer idNguoiMua;
    private String tenNguoiDk;
    private String sdtNguoiDk;
    private String soCMNDNguoiDk;

    // địa điểm đón trả
    private String diaDiemDuaDon;

    // thông tin tour
    private Integer[] arrIdTour;

    private Integer tongSoTien;

    // thông tin thẻ
    private String maAtm;
    private String passAtm;
}
