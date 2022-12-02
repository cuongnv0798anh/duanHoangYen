package com.example.duan_dulich.mapper;

import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.RateEntity;
import com.example.duan_dulich.model.entity.TourEntity;

public class CommentResponse extends RateEntity {
    public CommentResponse(Integer id, String comment, Account account, TourEntity tourEntity, int likes) {
        super(id, comment, account, tourEntity);
        this.countLike = likes;
    }

    public CommentResponse() {
    }

    private int countLike;

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public void mapFrom(RateEntity entity)
    {
        this.setId(entity.getId());
        this.setComment(entity.getComment());;
        this.setAccount(entity.getAccount());
        this.setTourEntity(entity.getTourEntity());
    }
}
