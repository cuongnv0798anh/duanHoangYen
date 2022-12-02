package com.example.duan_dulich.postcommnent;

import com.example.duan_dulich.mapper.CommentResponse;
import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.RateEntity;
import com.example.duan_dulich.model.entity.TourEntity;
import com.example.duan_dulich.postcommnent.dto.request.CreateCommentRequest;
import com.example.duan_dulich.repository.AccountRepository;
import com.example.duan_dulich.repository.Timrepository;
import com.example.duan_dulich.repository.TourReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentServiceIpm implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TourReponsitory tourReponsitory;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    Timrepository timrepository;

    @Override
    public ResponseCRUD create(CreateCommentRequest request) {
        RateEntity dataInsert = new RateEntity();
        dataInsert.setComment(request.getNoiDung());
        dataInsert.setAccount(accountRepository.getByIdAccount(request.getIdAcount()));
        dataInsert.setTourEntity(tourReponsitory.getById(request.getIdTour()));
        RateEntity data = commentRepository.save(dataInsert);
        return new ResponseCRUD(data);
    }

    @Override
    public ResponseCRUD delete(Integer id) {
        commentRepository.deleteById(id);
        return new  ResponseCRUD("DELETE SUCCESS");
    }

    @Override
    public ResponseCRUD getCommentByIdTour(Integer idTour) {
        List<RateEntity> listCommnent = commentRepository.getCommnentByIdTour(idTour);
        return new ResponseCRUD(listCommnent);
    }


    @Override
    public ResponseCRUD getCommentsByIdTour(Integer idTour) {
        List<RateEntity> listCommnent = commentRepository.getCommnentByIdTour(idTour);
        List<CommentResponse> comments = new ArrayList<>();
        for (int i = 0; i < listCommnent.size(); i++) {
            RateEntity comment = listCommnent.get(i);
            CommentResponse c = new CommentResponse();
            c.mapFrom(comment);
            c.setCountLike(timrepository.countTim(c.getId()));
            comments.add(c);
        }
        return new ResponseCRUD(comments);
    }
}
