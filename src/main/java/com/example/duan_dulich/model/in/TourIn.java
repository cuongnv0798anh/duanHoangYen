package com.example.duan_dulich.model.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TourIn {
    private Integer idAccount ;
    private Integer price ;
    private String description ;
    private String title ;
    private String totalMember ;
    private Date timeStart = new Date() ;
    private Integer totalDay ;
    private  boolean productStatus;
    private  int demTim;

    private MultipartFile[] image ;
}
