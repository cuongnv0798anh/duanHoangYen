package com.example.duan_dulich.thanhtoanhoadon.atmbuy;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.thanhtoanhoadon.dto.request.SearchAtmRequest;
import com.example.duan_dulich.thanhtoanhoadon.entity.AtmThanhToan;
import org.springframework.stereotype.Service;

@Service
public interface AtmService {
    AtmThanhToan getAtmByMaAndPass(SearchAtmRequest request);

    ResponseCRUD updateAtmKhiThanhToan(SearchAtmRequest request, Integer tienThanhToan);
}
