package com.example.duan_dulich.postcommnent;

import com.example.duan_dulich.model.entity.RateEntity;
import com.example.duan_dulich.uploadtinyfile.entityblog.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<RateEntity, Integer> {
    void deleteById(Integer id);

    @Query(value = "select * from post_comment cmt where cmt.id_tour = ?1  ", nativeQuery = true)
    List<RateEntity> getCommnentByIdTour(Integer idTour);


}
