package com.example.duan_dulich.thanhtoanhoadon.atmbuy;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.thanhtoanhoadon.dto.request.SearchAtmRequest;
import com.example.duan_dulich.thanhtoanhoadon.entity.AtmThanhToan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtmServiceIpm implements AtmService {

    @Autowired
    AtmRepository atmRepository;

    @Override
    public AtmThanhToan getAtmByMaAndPass(SearchAtmRequest request) {
        AtmThanhToan dataEntity = atmRepository.getAtmThanhToanByMaAtmAndPassAtm(request.getMaAtm(), request.getPassAtm());
        return dataEntity;
    }

    @Override
    public ResponseCRUD updateAtmKhiThanhToan(SearchAtmRequest request, Integer tienThanhToan) {
        AtmThanhToan dataEntity = atmRepository.getAtmThanhToanByMaAtmAndPassAtm(request.getMaAtm(), request.getPassAtm());
        Integer tienConlai = dataEntity.getSoDu()- tienThanhToan;
        dataEntity.setSoDu(tienConlai);
        atmRepository.save(dataEntity);
        return null;
    }
}
