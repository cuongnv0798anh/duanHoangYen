package com.example.duan_dulich.thanhtoanhoadon;

import com.example.duan_dulich.thanhtoanhoadon.dto.request.ThanhToanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/thanh-toan")
public class ThanhToanController {

    @Autowired
    ThanhToanService thanhToanService;

    @GetMapping("/hoa-don-tt/{idNguoiMua}")
    public ResponseEntity<?> getTourThanhToan(@PathVariable Integer idNguoiMua) {
        return new ResponseEntity<>(thanhToanService.getTourThanhToan(idNguoiMua), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> apiThanhToan(@RequestBody @Validated ThanhToanRequest request, boolean active) throws InterruptedException {
        return new ResponseEntity<>(thanhToanService.create(request, active), HttpStatus.ACCEPTED);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ThanhToanRequest request){
//        return new ResponseEntity<>(thanhToanService.update(id, request), HttpStatus.ACCEPTED);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
//        return new ResponseEntity<>(thanhToanService.delete(id), HttpStatus.ACCEPTED);
//    }

}
