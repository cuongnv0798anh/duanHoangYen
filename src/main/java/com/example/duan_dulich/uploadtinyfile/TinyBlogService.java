package com.example.duan_dulich.uploadtinyfile;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.uploadtinyfile.entityblog.BlogPost;
import com.example.duan_dulich.uploadtinyfile.request.CreateBlogRequest;
import com.example.duan_dulich.uploadtinyfile.request.SearchBlogRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface TinyBlogService {

    ResponseCRUD getBlog(SearchBlogRequest searchBlogRequest);

    ResponseCRUD createBlog(CreateBlogRequest createBlogRequest);

    ResponseCRUD delete(Integer id);

    ResponseCRUD update(Integer id, CreateBlogRequest createBlogRequest);

    ResponseCRUD getById(Integer id);

}
