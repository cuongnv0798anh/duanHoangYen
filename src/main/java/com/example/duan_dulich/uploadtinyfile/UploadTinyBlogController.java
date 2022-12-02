package com.example.duan_dulich.uploadtinyfile;

import com.example.duan_dulich.model.bo.SystemResponse;
import com.example.duan_dulich.model.in.LoginIn;
import com.example.duan_dulich.service.CRUD;
import com.example.duan_dulich.uploadtinyfile.request.CreateBlogRequest;
import com.example.duan_dulich.uploadtinyfile.request.SearchBlogRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/blog")
public class UploadTinyBlogController {

    @Autowired
    TinyBlogService tinyBlogService;

    @GetMapping
    public ResponseEntity<?> getBlog(SearchBlogRequest searchBlogRequest) {
        return new ResponseEntity<>(tinyBlogService.getBlog(searchBlogRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(tinyBlogService.getById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createBlog(@ModelAttribute CreateBlogRequest createBlogRequest){
        return new ResponseEntity<>(tinyBlogService.createBlog(createBlogRequest), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateBlog(@PathVariable Integer id, @ModelAttribute CreateBlogRequest createBlogRequest){
        return new ResponseEntity<>(tinyBlogService.update(id, createBlogRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        return new ResponseEntity<>(tinyBlogService.delete(id), HttpStatus.ACCEPTED);
    }
}
