package com.example.duan_dulich.postcommnent;

import com.example.duan_dulich.model.entity.RateEntity;
import com.example.duan_dulich.postcommnent.dto.request.CreateCommentRequest;
import com.example.duan_dulich.service.TimService;
import com.example.duan_dulich.thanhtoanhoadon.dto.request.ThanhToanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody CreateCommentRequest request) throws InterruptedException {
        return new ResponseEntity<>(commentService.create(request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        return new ResponseEntity<>(commentService.delete(id), HttpStatus.ACCEPTED);
    }
//
//    @GetMapping("/get/{id}")
//    public ResponseEntity<?> getCommentByIdTour(@PathVariable Integer id) {
//        return new ResponseEntity<>(commentService.getCommentByIdTour(id), HttpStatus.ACCEPTED);
//    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCommentsByIdTour(@PathVariable Integer id) {
        return new ResponseEntity<>(commentService.getCommentsByIdTour(id), HttpStatus.ACCEPTED);
    }
}
