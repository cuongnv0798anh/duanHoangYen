package com.example.duan_dulich.agency;

import com.example.duan_dulich.agency.dto.request.CreateAgencyRequest;
import com.example.duan_dulich.agency.entity.AgencyEntity;
import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.uploadtinyfile.request.CreateBlogRequest;
import com.example.duan_dulich.uploadtinyfile.request.SearchBlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgencyServiceIpm implements AgencyService {

    @Autowired
    AgencyRepository agencyRepository;

    @Override
    public ResponseCRUD getAgency() {
        List<AgencyEntity>  listData=  agencyRepository.findAll();
        return new ResponseCRUD(listData);
    }

    @Override
    public ResponseCRUD create(CreateAgencyRequest request) {
        AgencyEntity entity = new AgencyEntity();
        convertRequestToEntity(request, entity);
        AgencyEntity dataAdd = agencyRepository.save(entity);
        return new ResponseCRUD(dataAdd);
    }

    @Override
    public ResponseCRUD delete(Integer id) {
        agencyRepository.deleteById(id);
        return new ResponseCRUD("DELETE SUCCESS");
    }

    @Override
    public ResponseCRUD update(Integer id, CreateAgencyRequest request) {
        AgencyEntity entity = new AgencyEntity();
        convertRequestToEntity(request, entity, id);
        AgencyEntity dataAdd = agencyRepository.save(entity);
        return new ResponseCRUD(dataAdd);
    }

    private void convertRequestToEntity(CreateAgencyRequest request, AgencyEntity agencyEntity){
        agencyEntity.setTen(request.getTen());
        agencyEntity.setMota(request.getMota());
    }
    private void convertRequestToEntity(CreateAgencyRequest request, AgencyEntity agencyEntity,Integer id){
        agencyEntity.setTen(request.getTen());
        agencyEntity.setMota(request.getMota());
        agencyEntity.setId(id);
    }
}
