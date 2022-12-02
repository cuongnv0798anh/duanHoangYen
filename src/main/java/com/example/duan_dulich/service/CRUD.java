package com.example.duan_dulich.service;

import com.example.duan_dulich.model.bo.ResponseCRUD;

public interface CRUD<IN> {
    ResponseCRUD create(IN in);
    ResponseCRUD update(int id , IN in);
    ResponseCRUD get();
    ResponseCRUD delete(int id);


}
