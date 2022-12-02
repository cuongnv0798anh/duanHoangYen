package com.example.duan_dulich.common.uploadFile.model;

import com.example.duan_dulich.model.entity.ImageTour;

public class UploadMapper {
    public static UploadDto map(ImageTour imageTour)
    {
        UploadDto uploadDto = new UploadDto();
        uploadDto.setId(imageTour.getId());
        uploadDto.setFileName(imageTour.getFilename());
        uploadDto.setFile_url(imageTour.getFileUrl());
        return uploadDto ;
    }
}
