package com.example.duan_dulich.model.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTimRequest {
    private Integer idUser;
    private Integer idCmt;
    private int status;
}
