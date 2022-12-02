package com.example.duan_dulich.repository;

import com.example.duan_dulich.model.entity.Role;
import com.example.duan_dulich.model.entity.TimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Timrepository extends JpaRepository<TimEntity,Integer> {
    List<TimEntity> findAllByIdCmt(Integer id);

    TimEntity findAllByIdCmtAndIdUser(Integer idCmt, Integer idUser);

    @Query(value = "SELECT COUNT(id_cmt) FROM tim_entity WHERE id_cmt = ?1 AND status = 1", nativeQuery = true)
    Integer countTim(Integer id_cmt) ;
}
