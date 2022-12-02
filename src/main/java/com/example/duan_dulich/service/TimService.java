package com.example.duan_dulich.service;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.in.CreateTimRequest;
import org.springframework.stereotype.Service;

@Service
public interface TimService {

    ResponseCRUD clickChange(CreateTimRequest createTimRequest);


}
