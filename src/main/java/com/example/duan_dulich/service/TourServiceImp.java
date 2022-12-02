package com.example.duan_dulich.service;

import com.example.duan_dulich.common.uploadFile.services.UploadService;
import com.example.duan_dulich.mapper.TourMapper;
import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.bo.TourDto;
import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.ImageTour;
import com.example.duan_dulich.model.entity.TourEntity;
import com.example.duan_dulich.model.in.TourIn;
import com.example.duan_dulich.repository.AccountRepository;
import com.example.duan_dulich.repository.ImageReponsitory;
import com.example.duan_dulich.repository.TourReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service("tour")
public class TourServiceImp implements CRUD<TourIn> {
@Autowired
private TourReponsitory tourReponsitory ;
@Autowired
private UploadService uploadService ;
@Autowired
private AccountRepository accountRepository ;
@Autowired
private ImageReponsitory imageReponsitory ;
    @Override
    public ResponseCRUD create(TourIn tourIn) {
        Collection<ImageTour> imageTours = uploadService.saveFile(tourIn.getImage());
//        ModelMapper modelMapper = new ModelMapper();
//      TourEntity tourEntity = modelMapper.map(tourIn,TourEntity.class);
        TourEntity tourEntity = new TourEntity();
        setUpData(tourEntity, tourIn);
      tourEntity.setAccount(accountRepository.findById(tourIn.getIdAccount()).get());
      imageTours.forEach(imageTour -> imageTour.setTourEntity(tourEntity));
      tourEntity.setImageTours(imageTours);
      tourEntity.setDemTim(tourIn.getDemTim());
      tourReponsitory.save(tourEntity);

      return new ResponseCRUD(null) ;
    }

    @Override
@Transactional
    public ResponseCRUD update(int id, TourIn tourIn) {
        TourEntity entity = tourReponsitory.findById(id).get();
         imageReponsitory.deleteImageTourByTourEntity(entity);
        ModelMapper modelMapper = new ModelMapper() ;
        Collection<ImageTour> imageTours = uploadService.saveFile(tourIn.getImage());
      TourEntity  tourEntity = modelMapper.map(tourIn,TourEntity.class);
        tourEntity.setId(id);
        tourEntity.setImageTours(imageTours);
        imageTours.forEach(imageTour -> imageTour.setTourEntity(tourEntity));
        tourReponsitory.save(tourEntity);
        return new ResponseCRUD(null) ;
    }
    @Override
    public ResponseCRUD get() {
       List<TourDto> tourEntities= tourReponsitory.findAll().stream().map(TourMapper::map).collect(Collectors.toList());
        return new ResponseCRUD(tourEntities) ;
    }

    @Override
    public ResponseCRUD delete(int id) {
       tourReponsitory.deleteById(id);
        return new ResponseCRUD(null) ;
    }

    private void setUpData(TourEntity tourEntity, TourIn tourIn){
        tourEntity.setTitle(tourIn.getTitle());
        tourEntity.setPrice(tourIn.getPrice());
        tourEntity.setDescription(tourIn.getDescription());
        tourEntity.setTotalMember(tourIn.getTotalMember());
        tourEntity.setTimeStart(tourIn.getTimeStart());
        tourEntity.setProductStatus(tourIn.isProductStatus());
        tourEntity.setTotalDay(tourIn.getTotalDay());

    }

}
