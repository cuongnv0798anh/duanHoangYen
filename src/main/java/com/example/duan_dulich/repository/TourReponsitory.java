package com.example.duan_dulich.repository;

import com.example.duan_dulich.common.Date.DateUlti;
import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.entity.TourEntity;
import com.example.duan_dulich.uploadtinyfile.entityblog.BlogPost;
import com.example.duan_dulich.uploadtinyfile.request.SearchBlogRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TourReponsitory extends JpaRepository<TourEntity,Integer> {
    TourEntity getById(Integer id);

//    List<TourEntity>  getAllByIdAgency(Integer id);
    TourEntity getAllById(Integer id);
    List<TourEntity> findTourEntitiesByAccountIdAccount(Integer id);

    @Query(value = "select * from tours   where (?1 is null  or tours.title like ?1) and (?2 is null or tours.time_start = ?2) and (?3 is null or tours.price = ?3) and (?4 is null or tours.total_day = ?4) ", nativeQuery = true)
    List<TourEntity> getTourByRequest(String diaDiem, Date ngay, Integer gia, Integer totalDay);

    @Query(value = "select * from tours order by dem_tim desc limit 3", nativeQuery = true)
    List<TourEntity>getTourHot();



    @Query(value = "select sum(tong_so_tien) as tongtien from account join tours on account.id_account = tours.account_id_account join hoa_don on tours.id = hoa_don.id_tour where tours.account_id_account= ?1", nativeQuery = true)
    int tongSoTienAngency(Integer id);

    @Query(value = "select count(tours.account_id_account) as tongTour from account join tours on account.id_account = tours.account_id_account join hoa_don on tours.id = hoa_don.id_tour where tours.account_id_account= ?1", nativeQuery = true)
    int tongSoTourAngency(Integer id);
}
