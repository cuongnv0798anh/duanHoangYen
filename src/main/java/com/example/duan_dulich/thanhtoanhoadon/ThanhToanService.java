package com.example.duan_dulich.thanhtoanhoadon;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.thanhtoanhoadon.dto.request.ThanhToanRequest;
import org.springframework.stereotype.Service;

@Service
public interface ThanhToanService {

    ResponseCRUD getTourThanhToan(Integer idNguoiMua);

    ResponseCRUD create(ThanhToanRequest request, boolean active) throws InterruptedException;

//    ResponseCRUD delete(Integer id);
//
//    ResponseCRUD update(Integer id, ThanhToanRequest request);
}
