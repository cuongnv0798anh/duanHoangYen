package com.example.duan_dulich.model.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

@NoArgsConstructor
public class ResponseCRUD {
    private String messenger;
    private String errorCode;
    private Object data;

    public ResponseCRUD(Object data) {
        this.data = data;
        this.errorCode = "200" ;
        this.messenger= "Thành công";
    }

    public ResponseCRUD(String messenger, String errorCode) {
        this.messenger = messenger;
        this.errorCode = errorCode;
    }
}
