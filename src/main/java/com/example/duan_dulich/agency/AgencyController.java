package com.example.duan_dulich.agency;

import com.example.duan_dulich.agency.dto.request.CreateAgencyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/agency")
public class AgencyController {

    @Autowired
    AgencyService agencyService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(agencyService.getAgency(), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody CreateAgencyRequest request){
        return new ResponseEntity<>(agencyService.create(request), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CreateAgencyRequest request){
        return new ResponseEntity<>(agencyService.update(id, request), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        return new ResponseEntity<>(agencyService.delete(id), HttpStatus.ACCEPTED);
    }

}
