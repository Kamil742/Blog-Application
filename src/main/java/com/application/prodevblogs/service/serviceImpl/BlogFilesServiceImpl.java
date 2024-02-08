package com.application.prodevblogs.service.serviceImpl;


import com.application.prodevblogs.exceptions.BlogFilesNotFoundException;
import com.application.prodevblogs.model.BlogFiles;
import com.application.prodevblogs.repository.BlogFilesRepository;
import com.application.prodevblogs.service.BlogFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogFilesServiceImpl implements BlogFilesService {
    private final BlogFilesRepository blogFilesRepository;
    @Autowired
    public BlogFilesServiceImpl(BlogFilesRepository blogFilesRepository) {
        this.blogFilesRepository = blogFilesRepository;
    }

    @Override
    public BlogFiles createBlogFiles(BlogFiles blogFiles) {
        try{
            return blogFilesRepository.save(blogFiles);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BlogFiles getBlogFilesById(Long BlogFileId) {
        Optional<BlogFiles> blogFilesOptional = blogFilesRepository.findById(BlogFileId);
        return blogFilesOptional.orElse(null);
    }

    @Override
    public List<BlogFiles> getAllBlogFiles() {
        return blogFilesRepository.findAll();
    }

    @Override
    public BlogFiles updateBlogFiles(Long BlogFileId, BlogFiles blogFiles) throws BlogFilesNotFoundException {
        try {
            Optional<BlogFiles> existingBlogFilesOptional = blogFilesRepository.findById(BlogFileId);

            if (existingBlogFilesOptional.isPresent()) {
                BlogFiles existingBlogFiles = existingBlogFilesOptional.get();
                // implement blog files updating things

                return blogFilesRepository.save(existingBlogFiles);
            } else {
                throw new BlogFilesNotFoundException("BlogFiles with ID " + BlogFileId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating blogFiles", e);
        }
    }

    @Override
    public void deleteBlogFiles(Long BlogFileId) throws BlogFilesNotFoundException {
        try {
            Optional<BlogFiles> blogFilesOptional = blogFilesRepository.findById(BlogFileId);

            if (blogFilesOptional.isPresent()) {
                blogFilesRepository.deleteById(BlogFileId);
            } else {
                throw new BlogFilesNotFoundException("BlogFiles with ID " + BlogFileId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting blogFiles", e);
        }
    }

}
