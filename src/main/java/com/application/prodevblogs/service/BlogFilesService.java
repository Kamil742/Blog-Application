package com.application.prodevblogs.service;

import com.application.prodevblogs.exceptions.BlogFilesNotFoundException;
import com.application.prodevblogs.model.BlogFiles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogFilesService {
    BlogFiles createBlogFiles(BlogFiles blogFiles);
    BlogFiles getBlogFilesById(Long blogFileId);
    List<BlogFiles> getAllBlogFiles();
    BlogFiles updateBlogFiles(Long blogFileId, BlogFiles blogFiles) throws BlogFilesNotFoundException;
    void deleteBlogFiles(Long blogFileId) throws BlogFilesNotFoundException;
}
