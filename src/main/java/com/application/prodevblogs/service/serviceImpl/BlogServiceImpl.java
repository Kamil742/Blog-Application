package com.application.prodevblogs.service.serviceImpl;

import com.application.prodevblogs.exceptions.BlogNotFoundException;
import com.application.prodevblogs.exceptions.UserProfileNotFoundException;
import com.application.prodevblogs.model.Blog;
import com.application.prodevblogs.model.UserProfile;
import com.application.prodevblogs.repository.BlogFilesRepository;
import com.application.prodevblogs.repository.BlogRepository;
import com.application.prodevblogs.repository.UserProfileRepository;
import com.application.prodevblogs.service.BlogFilesService;
import com.application.prodevblogs.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserProfileRepository userProfileRepository;
    private final BlogFilesRepository blogFilesRepository;
    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, BlogFilesRepository blogFilesRepository, UserProfileRepository userProfileRepository) {
        this.blogRepository = blogRepository;
        this.blogFilesRepository = blogFilesRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public Blog createBlog(Blog blog) {
        try {
            blog.setDate(LocalDate.now());
            blog.setUserProfile(userProfileRepository.findById(blog.getUserProfile().getUserId()).orElseThrow(() -> new UserProfileNotFoundException("User with ID " + blog.getUserProfile().getUserId() + " not found")));

            // Split content into paragraphs and store them
            List<String> paragraphs = Arrays.asList(blog.getContent().split("\n"));
            blog.setContent(String.join("\n", paragraphs));

            blog.setBlogFiles(blogFilesRepository.save(blog.getBlogFiles()));
            blog.getUserProfile().setSizeAvailable(blog.getUserProfile().getSizeAvailable() - blog.getBlogFiles().getSize());
            return blogRepository.save(blog);
        } catch (Exception | UserProfileNotFoundException e) {
            throw new RuntimeException("Error creating blog", e);
        }
    }

    @Override
    public Blog getBlogById(Long blogId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            // Concatenate paragraphs when retrieving a single blog
            List<String> paragraphs = Arrays.asList(blog.getContent().split("\n"));
            blog.setContent(String.join("\n", paragraphs));
            return blog;
        } else {
            return null;
        }
    }


    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        // Concatenate paragraphs when retrieving blogs
        blogs.forEach(blog -> {
            List<String> paragraphs = Arrays.asList(blog.getContent().split("\n"));
            blog.setContent(String.join("\n", paragraphs));
        });
        return blogs;
    }

    @Override
    public List<Blog> getAllBlogsByUser(Long userId) {
        try {
            Optional<UserProfile> optionalUser = userProfileRepository.findById(userId);

            if (optionalUser.isPresent()) {
                UserProfile user = optionalUser.get();
                List<Blog> blogs = blogRepository.findAllByUserProfile(user);
                // Concatenate paragraphs when retrieving blogs
                blogs.forEach(blog -> {
                    List<String> paragraphs = Arrays.asList(blog.getContent().split("\n"));
                    blog.setContent(String.join("\n", paragraphs));
                });
                return blogs;
            } else {
                throw new UserProfileNotFoundException("User with ID " + userId + " not found");
            }
        } catch (UserProfileNotFoundException e) {
            throw new RuntimeException("User with ID " + userId + " not found", e);
        }
    }

    @Override
    public Blog updateBlog(Long blogId, Blog blog) throws BlogNotFoundException {
        try {
            Optional<Blog> existingBlogOptional = blogRepository.findById(blogId);

            if (existingBlogOptional.isPresent()) {
                Blog existingBlog = existingBlogOptional.get();
                existingBlog.setTitle(blog.getTitle());
                existingBlog.setCategory(blog.getCategory());
                existingBlog.setImageUrl(blog.getImageUrl());

                // Split content into paragraphs and store them
                List<String> paragraphs = Arrays.asList(blog.getContent().split("\n"));
                existingBlog.setContent(String.join("\n", paragraphs));

                return blogRepository.save(existingBlog);
            } else {
                throw new BlogNotFoundException("Blog with ID " + blogId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating blog", e);
        }
    }

    @Override
    public void deleteBlog(Long blogId) throws BlogNotFoundException {
        try {
            Optional<Blog> blogOptional = blogRepository.findById(blogId);

            if (blogOptional.isPresent()) {
                blogRepository.deleteById(blogId);
            } else {
                throw new BlogNotFoundException("Blog with ID " + blogId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting blog", e);
        }
    }
}