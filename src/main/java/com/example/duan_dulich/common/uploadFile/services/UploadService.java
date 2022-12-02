package com.example.duan_dulich.common.uploadFile.services;

import com.example.duan_dulich.common.uploadFile.utils.respon.DataRespon;
import com.example.duan_dulich.common.uploadFile.utils.respon.UploadRespon;
import com.example.duan_dulich.model.entity.ImageTour;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Service
public interface UploadService {
    Collection<ImageTour> saveFile(MultipartFile[] file);
    Resource loadFileAsResource(String fileName);
    DataRespon getAll();
    ResponseEntity delete(Integer id);
    ResponseEntity update(Integer id , MultipartFile file);
}
