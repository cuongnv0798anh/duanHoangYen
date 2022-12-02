package com.example.duan_dulich.service;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.entity.TimEntity;
import com.example.duan_dulich.model.in.CreateTimRequest;
import com.example.duan_dulich.repository.Timrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimServiceIpm implements TimService {
    @Autowired
    Timrepository timrepository;

    @Override
    public ResponseCRUD clickChange(CreateTimRequest request) {
        TimEntity timCheck = timrepository.findAllByIdCmtAndIdUser(request.getIdCmt(), request.getIdUser());
        TimEntity timEntity = new TimEntity();
        if (timCheck == null){
            setUpData(request, timEntity);
            TimEntity tim = timrepository.save(timEntity);
            return new ResponseCRUD(tim);
        }else{
//            setUpData(request, timEntity);
            if (timCheck.getStatus() == 1){
                timCheck.setStatus(0);
                TimEntity tim = timrepository.save(timCheck);
                return new ResponseCRUD(tim);
            }else{
                timCheck.setStatus(1);
                TimEntity tim = timrepository.save(timCheck);
                return new ResponseCRUD(tim);
            }
        }
    }

    private void setUpData(CreateTimRequest request, TimEntity timEntity){
        timEntity.setStatus(request.getStatus());
        timEntity.setIdCmt(request.getIdCmt());
        timEntity.setIdUser(request.getIdUser());
    }
}
