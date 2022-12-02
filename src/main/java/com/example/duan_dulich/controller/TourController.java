package com.example.duan_dulich.controller;

import com.example.duan_dulich.common.Date.DateUlti;
import com.example.duan_dulich.mapper.TourMapper;
import com.example.duan_dulich.model.bo.Respon;
import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.bo.TourDto;
import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.Role;
import com.example.duan_dulich.model.entity.TourEntity;
import com.example.duan_dulich.model.in.SearchTourRequest;
import com.example.duan_dulich.model.in.TourIn;
import com.example.duan_dulich.repository.AccountRepository;
import com.example.duan_dulich.repository.TourReponsitory;
import com.example.duan_dulich.service.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tour")
public class TourController {
    @Autowired
    private AccountRepository accountRepository ;
    @Autowired
    private TourReponsitory tourReponsitory ;
    @Autowired
    @Qualifier("tour")
    private CRUD crud;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity createTour(@ModelAttribute TourIn tourIn) {
        return new ResponseEntity<>(crud.create(tourIn), HttpStatus.ACCEPTED);
    }

    @GetMapping
    ResponseEntity getTour() {
        return new ResponseEntity<>(crud.get(), HttpStatus.ACCEPTED);
    }
    @DeleteMapping()
    ResponseEntity delTour(@RequestParam("id") Integer id)
    {
        return new ResponseEntity<>(crud.delete(id),HttpStatus.OK);
    }
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity updateTour(@RequestParam("id") Integer id , @ModelAttribute TourIn tourIn)
    {
        return new ResponseEntity<>(crud.update(id,tourIn),HttpStatus.OK);
    }
    @PostMapping("/verify")
    ResponseEntity verify(@RequestParam("id") Integer id) {
        TourEntity tourEntity = tourReponsitory.getById(id);
        tourEntity.setId(id);
        tourEntity.setProductStatus(true);
        tourReponsitory.save(tourEntity);
        return ResponseEntity.ok(new ResponseCRUD(null));
    }
    @GetMapping("/getTour")
    ResponseEntity getTourbyID(@RequestParam Integer id){
//        TourEntity tourEntity = tourReponsitory.getById(id);
//        return ResponseEntity.ok(new ResponseCRUD(tourEntity));
        TourEntity tourEntity = tourReponsitory.getAllById(id);
        TourDto tourDto = TourMapper.map(tourEntity);
        return ResponseEntity.ok(new ResponseCRUD(tourDto));



    }

    @GetMapping("/get-tour-agency")
    ResponseEntity getTourByRequest(Integer id) {

        Account account = accountRepository.getByIdAccount(id);
        Collection<Role> collection = account.getRoles() ;
        List<TourDto> list = null;
        for (Role role : collection)
        {
            if (!role.getNameRole().equals("ROLE_AGENCY"))
            {
                return new ResponseEntity<>(new ResponseCRUD("ID nay khong phai Agency","1000"),HttpStatus.OK);
            }
           
            list = tourReponsitory.findTourEntitiesByAccountIdAccount(id).stream().map(TourMapper::map).collect(Collectors.toList());
           
        }
        return new ResponseEntity<>(new ResponseCRUD(list) , HttpStatus.OK);

    }

    @GetMapping("/get-home")
    ResponseEntity getTourHome(SearchTourRequest request) throws ParseException {
        return new ResponseEntity<>(tourReponsitory.getTourByRequest(setDiaDiem(request.getDiaDiem()), DateUlti.stringToDate(request.getNgay())
                , request.getPrice(), request.getTotalDay()).stream().map(TourMapper::map).collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }

    private  String setDiaDiem(String diaDiem){
        return ("%"+diaDiem.trim()+"%");
    }

//    @GetMapping("/get-tour-home")
//    ResponseEntity getHomePage(SearchTourRequest request) {
//        return new ResponseEntity<>(tourReponsitory.getHomePage(request), HttpStatus.ACCEPTED);
//    }





    @PostMapping("/postTim")
    ResponseEntity postTim(@RequestParam Integer idAccount , @RequestParam Integer idTour , @RequestParam Boolean status)  {
        int i=0;
        int dem=0;
        Account account = accountRepository.getByIdAccount(idAccount);
        TourEntity tour = tourReponsitory.getById(idTour);
        if(account==null&&tour==null){
            return new ResponseEntity<>(0,HttpStatus.OK);
        }
        if(status==true){
            dem=i+1;
            tour.setDemTim(dem);
            tourReponsitory.save(tour);
        }else {
            dem=i-1;
            tour.setDemTim(dem);
            tourReponsitory.save(tour);
        }
        return new ResponseEntity<>(1, HttpStatus.OK);

    }

    @GetMapping("/tourHot")
    ResponseEntity getTourHost(){
        List<TourDto> list = tourReponsitory.getTourHot().stream().map(TourMapper::map).collect(Collectors.toList());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getMoney/{id}")
    Respon getMoney(@PathVariable Integer id){
         int getMoneyTour = 0;
         int getTongTour = 0;
         if(tourReponsitory.existsById(id)==false){
             return new Respon(1,"Not exits account");
         }
        getMoneyTour = tourReponsitory.tongSoTienAngency(id);
        getTongTour = tourReponsitory.tongSoTourAngency(id);
        return new Respon(0,"Success",getMoneyTour,getTongTour);
    }
}


