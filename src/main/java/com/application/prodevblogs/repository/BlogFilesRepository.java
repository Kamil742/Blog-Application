package com.application.prodevblogs.repository;

import com.application.prodevblogs.model.BlogFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogFilesRepository extends JpaRepository<BlogFiles, Long> {
    boolean existsByBlogFileId(Long BlogFileId);
}
