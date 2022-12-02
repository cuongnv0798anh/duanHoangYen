package com.example.duan_dulich.uploadtinyfile;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.uploadtinyfile.entityblog.BlogPost;
import com.example.duan_dulich.uploadtinyfile.entityblog.ImageBlog;
import com.example.duan_dulich.uploadtinyfile.request.CreateBlogRequest;
import com.example.duan_dulich.uploadtinyfile.request.SearchBlogRequest;
import com.example.duan_dulich.uploadtinyfile.uploadimageblog.UploadImageBlogRepository;
import com.example.duan_dulich.uploadtinyfile.uploadimageblog.UploadImageBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class TinyBlogServiceIpm implements TinyBlogService {

    @Autowired
    private TinyBlogRepository tinyBlogRepository;

    @Autowired
    private UploadImageBlogService uploadImageBlogService;

    @Autowired
    private UploadImageBlogRepository uploadImageBlogRepository;



    @Override
    public ResponseCRUD getBlog(SearchBlogRequest searchBlogRequest) {
        List<BlogPost> blogPostList= new ArrayList<>(tinyBlogRepository.findAll());
        return new ResponseCRUD(blogPostList) ;
    }

    @Override
    public ResponseCRUD createBlog(CreateBlogRequest createBlogRequest) {
        BlogPost blogEntity = new BlogPost();
        setBlogEntity(createBlogRequest, blogEntity);
        Collection<ImageBlog> imageBlogs = uploadImageBlogService.saveFile(createBlogRequest.getImage());
        imageBlogs.forEach(imageBlog -> imageBlog.setBlogPost(blogEntity));
        blogEntity.setImageBlogs(imageBlogs);
        BlogPost response =  tinyBlogRepository.save(blogEntity);
        return new  ResponseCRUD(response);
    }

    @Override
    public ResponseCRUD delete(Integer id) {
        Optional<BlogPost> blogPostDelete = tinyBlogRepository.findById(id);
        tinyBlogRepository.deleteById(id);
        return new ResponseCRUD(blogPostDelete) ;
    }
    @Override
    public ResponseCRUD update(Integer id, CreateBlogRequest createBlogRequest) {
        BlogPost blogPost = tinyBlogRepository.findById(id).get();
        uploadImageBlogRepository.deleteImageBlogByBlogPost(blogPost);
        BlogPost blogEntity = new BlogPost();
        setBlogEntity(createBlogRequest, blogEntity,id);
        Collection<ImageBlog> imageBlogs = uploadImageBlogService.saveFile(createBlogRequest.getImage());
        imageBlogs.forEach(imageBlog -> imageBlog.setBlogPost(blogEntity));
        blogEntity.setImageBlogs(imageBlogs);
        BlogPost response =  tinyBlogRepository.save(blogEntity);
        return new  ResponseCRUD(response);
    }

    @Override
    public ResponseCRUD getById(Integer id) {
        BlogPost blogPost =  tinyBlogRepository.getById(id);
        return new  ResponseCRUD(blogPost);
    }


    private void setBlogEntity ( CreateBlogRequest request, BlogPost blogPost){
        blogPost.setTitle(request.getTitle());
        blogPost.setContent(request.getContent());
        blogPost.setCreatedBy(request.getCreatedBy());
        blogPost.setCreatedDate(request.getCreatedDate());
    }

    private void setBlogEntity ( CreateBlogRequest request, BlogPost blogPost, Integer id){
        blogPost.setTitle(request.getTitle());
        blogPost.setContent(request.getContent());
        blogPost.setUpdatedBy(request.getUpdateBy());
        blogPost.setUpdateDate(request.getUpdatedDate());
        blogPost.setId(id);
    }
}
