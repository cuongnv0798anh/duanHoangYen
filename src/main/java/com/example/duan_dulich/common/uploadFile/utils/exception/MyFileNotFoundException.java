package com.example.duan_dulich.common.uploadFile.utils.exception;

public class MyFileNotFoundException extends  RuntimeException{
    public MyFileNotFoundException(String message)
    {
        super(message);
    }
    public  MyFileNotFoundException(String message , Throwable throwable)
    {
        super(message,throwable);
    }
}
