package com.example.duan_dulich.model.bo;

public class SystemResponse<T> {
    private int status;

    private String error;
    private String message;

    private String userName;
    private T data;
    private Object role ;
    private  Integer id ;
    public SystemResponse() {
    }

    public SystemResponse(String message) {
        this.message = message;
    }

    public SystemResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public SystemResponse(int status, String error, String message, T data) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.userName = userName;
        this.data = data;
    }

    public SystemResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public SystemResponse(int status, String error, String message, String userName, T data, Object role, Integer id) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.userName = userName;
        this.data = data;
        this.role = role;
        this.id = id ;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

