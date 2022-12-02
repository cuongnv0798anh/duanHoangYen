package com.example.duan_dulich.uploadtinyfile;

import com.example.duan_dulich.uploadtinyfile.entityblog.BlogPost;
import com.example.duan_dulich.uploadtinyfile.request.SearchBlogRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TinyBlogRepository extends JpaRepository<BlogPost, Integer> {
    Optional<BlogPost> findById(Integer id);

}
