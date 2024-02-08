package com.application.prodevblogs.service;

import com.application.prodevblogs.exceptions.UserProfileNotFoundException;
import com.application.prodevblogs.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserProfileService {
    UserProfile createUserProfile(UserProfile userProfile) throws RuntimeException ;
    UserProfile getUserProfileById(Long userProfileId) throws UserProfileNotFoundException ;
    UserProfile getUserProfileByEmailId(String email) throws UserProfileNotFoundException;
    List<UserProfile> getAllUserProfiles()  ;
    UserProfile updateUserProfile(Long userProfileId, UserProfile userProfile) throws UserProfileNotFoundException;
    void deleteUserProfile(Long userProfileId) throws UserProfileNotFoundException;


}
