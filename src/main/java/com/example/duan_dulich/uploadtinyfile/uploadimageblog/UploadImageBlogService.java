package com.example.duan_dulich.uploadtinyfile.uploadimageblog;

import com.example.duan_dulich.common.uploadFile.utils.respon.DataRespon;
import com.example.duan_dulich.model.entity.ImageTour;
import com.example.duan_dulich.uploadtinyfile.entityblog.BlogPost;
import com.example.duan_dulich.uploadtinyfile.entityblog.ImageBlog;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public interface UploadImageBlogService {

    Collection<ImageBlog> saveFile(MultipartFile[] file);
    Resource loadFileAsResource(String fileName);
//    DataRespon getAll();
    ResponseEntity delete(Integer id);
    ResponseEntity update(Integer id , MultipartFile file);

}
