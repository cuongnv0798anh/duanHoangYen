package com.example.duan_dulich.common.uploadFile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadDto {
    private Integer id ;
    private  String fileName ;
    private String file_url ;
}
