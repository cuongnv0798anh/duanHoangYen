package com.example.duan_dulich.mapper;

import com.example.duan_dulich.model.bo.TourDto;
import com.example.duan_dulich.model.entity.TourEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TourMapper {
    public TourDto map(TourEntity tourEntity){
        TourDto tourDto = new TourDto();
        tourDto.setId(tourEntity.getId());
        tourDto.setCreateBy(tourEntity.getAccount().getUserName());
        tourDto.setDescription(tourEntity.getDescription());
        tourDto.setPrice(tourEntity.getPrice());
        tourDto.setTimeStart(tourEntity.getTimeStart());
        tourDto.setTitle(tourEntity.getTitle());
        tourDto.setIdCompany(tourEntity.getIdCompany());
        tourDto.setProductStatus(tourEntity.isProductStatus());
        tourDto.setTotalDay(tourEntity.getTotalDay());
        tourDto.setTotalMember(tourEntity.getTotalMember());
        tourDto.setImage(tourEntity.getImageTours());
        tourDto.setDemTim(tourEntity.getDemTim());
        return tourDto;
    }
}
