package com.application.prodevblogs.repository;


import com.application.prodevblogs.model.Blog;
import com.application.prodevblogs.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository< Blog, Long> {
    boolean existsByBlogId(Long blogId);
    List<Blog> findAllByUserProfile(UserProfile user);

}
