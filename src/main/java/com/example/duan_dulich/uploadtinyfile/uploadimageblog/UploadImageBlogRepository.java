package com.example.duan_dulich.uploadtinyfile.uploadimageblog;

import com.example.duan_dulich.uploadtinyfile.entityblog.BlogPost;
import com.example.duan_dulich.uploadtinyfile.entityblog.ImageBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UploadImageBlogRepository extends JpaRepository<ImageBlog, Integer> {

    ImageBlog getById(Integer id );

    void deleteImageBlogByBlogPost(BlogPost blogPost);


//    void deleteByBlogPost(BlogPost blogPost);
//    void deleteImageBlogByBlogPost(BlogPost blogPost);
}
