package com.example.duan_dulich.postcommnent.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {

    private String noiDung;

    private Integer idAcount;

    private Integer idTour;
}
