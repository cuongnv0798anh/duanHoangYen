package com.example.duan_dulich.uploadtinyfile.entityblog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "imageblog")
public class ImageBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String fileUrl ;
    @Column
    private String filename ;
    @Column
    private String fileType ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private BlogPost blogPost ;

    public ImageBlog(String filename, String fileUrl, String fileType) {
        this.filename = filename;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }
}
