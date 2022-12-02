package com.example.duan_dulich.repository;

import com.example.duan_dulich.model.entity.ImageTour;
import com.example.duan_dulich.model.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageReponsitory extends JpaRepository<ImageTour,Integer> {
    ImageTour getById(Integer id );
    void deleteImageTourByTourEntity(final TourEntity tourEntity);
}
