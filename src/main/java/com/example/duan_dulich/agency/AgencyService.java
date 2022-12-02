package com.example.duan_dulich.agency;

import com.example.duan_dulich.agency.dto.request.CreateAgencyRequest;
import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.uploadtinyfile.request.CreateBlogRequest;
import com.example.duan_dulich.uploadtinyfile.request.SearchBlogRequest;
import org.springframework.stereotype.Service;

@Service
public interface AgencyService {

    ResponseCRUD getAgency();

    ResponseCRUD create(CreateAgencyRequest request);

    ResponseCRUD delete(Integer id);

    ResponseCRUD update(Integer id, CreateAgencyRequest request);
}
