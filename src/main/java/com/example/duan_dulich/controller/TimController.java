package com.example.duan_dulich.controller;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.in.CreateTimRequest;
import com.example.duan_dulich.model.in.SearchTourRequest;
import com.example.duan_dulich.repository.Timrepository;
import com.example.duan_dulich.service.TimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/like")
public class TimController {
    @Autowired
    TimService timService;
    @Autowired
    Timrepository timrepository ;
    @GetMapping
    ResponseEntity getTourByRequest(CreateTimRequest request) {
        return new ResponseEntity<>(timService.clickChange(request), HttpStatus.ACCEPTED);
    }
    @GetMapping("/count/{id_cmt}")
    ResponseEntity countTim (@PathVariable Integer id_cmt){
        return new ResponseEntity<>(new ResponseCRUD(timrepository.countTim(id_cmt)),HttpStatus.OK);
    }
}
