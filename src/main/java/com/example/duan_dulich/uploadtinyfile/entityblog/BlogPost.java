package com.example.duan_dulich.uploadtinyfile.entityblog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "blog")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String createdBy;
    @Column
    private Date createdDate;

    @Column
    private String updatedBy;

    @Column
    private Date updateDate;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL)
    private Collection<ImageBlog> imageBlogs = new ArrayList<>();
}
