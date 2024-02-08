package com.application.prodevblogs.service;

import com.application.prodevblogs.exceptions.BlogNotFoundException;
import com.application.prodevblogs.model.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    Blog createBlog(Blog blog);
    Blog getBlogById(Long blogId);
    List<Blog> getAllBlogs();
    Blog updateBlog(Long blogId, Blog blog) throws BlogNotFoundException;
    void deleteBlog(Long blogId) throws BlogNotFoundException;
    List<Blog> getAllBlogsByUser(Long userId);
}
