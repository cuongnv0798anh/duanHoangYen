package com.example.duan_dulich.uploadtinyfile.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class CreateBlogRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;
    //    private Date timeDate = new Date();
    private MultipartFile[] image;


    private String createdBy;

    private Date createdDate = new Date();

    private String updateBy;

    private Date updatedDate = new Date();
}
