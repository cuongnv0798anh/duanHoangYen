package com.example.duan_dulich.model.bo;

import lombok.Data;

@Data
public class ResponAuth{
    private boolean status;
    private String messenger;
    private String error;
    private Object data;

    public ResponAuth(boolean status, String messenger, String error, Object data) {
        this.status = status;
        this.messenger = messenger;
        this.error = error;
        this.data = data;
    }

    public ResponAuth(boolean status, String messenger) {
        this.status = status;
        this.messenger = messenger;
    }

    public ResponAuth(String error, String messenger) {
        this.status = status;
        this.error = error;
    }
    public ResponAuth(Object data, String messenger) {
        this.data = data;
        this.messenger = messenger;
    }
}
