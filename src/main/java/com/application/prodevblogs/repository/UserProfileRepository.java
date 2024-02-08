package com.application.prodevblogs.repository;


import com.application.prodevblogs.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByEmailId(String emailId);

    UserProfile findByEmailId( String emailId);
}
