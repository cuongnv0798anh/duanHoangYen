package com.example.duan_dulich.postcommnent;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.postcommnent.dto.request.CreateCommentRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    ResponseCRUD create(CreateCommentRequest request);

    ResponseCRUD delete(Integer id);

    ResponseCRUD getCommentByIdTour(Integer idTour);
    ResponseCRUD getCommentsByIdTour(Integer idTour);
}
