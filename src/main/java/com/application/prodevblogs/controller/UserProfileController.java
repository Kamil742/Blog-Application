package com.application.prodevblogs.controller;

import com.application.prodevblogs.exceptions.UserProfileNotFoundException;
import com.application.prodevblogs.model.UserProfile;
import com.application.prodevblogs.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserProfileController {
    
    private final UserProfileService userProfileService;
    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> createUserProfile(
            @RequestBody UserProfile userProfile) throws RuntimeException {
        try{
            UserProfile createdUserProfile = userProfileService.createUserProfile(userProfile);
            return new ResponseEntity<>("UserProfile Created Successfully", HttpStatus.CREATED);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/{userProfileId}")
    public ResponseEntity<?> getUserProfile(
            @PathVariable Long userProfileId) throws RuntimeException {
        try{
            UserProfile userProfile = userProfileService.getUserProfileById(userProfileId);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }catch (Exception | UserProfileNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUser/{email:.+}")
    public ResponseEntity<?> getUserProfile(
            @PathVariable String  email) throws RuntimeException {
        try{
            UserProfile userProfile = userProfileService.getUserProfileByEmailId(email);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }catch (Exception | UserProfileNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getUserProfiles")
    public ResponseEntity<List<UserProfile>> getAllUserProfiles() throws RuntimeException {
        List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    
    @PutMapping("/update/{userProfileId}")
    public ResponseEntity<UserProfile> updateUserProfile(
            @PathVariable Long userProfileId, @RequestBody UserProfile userProfile) throws RuntimeException, UserProfileNotFoundException {
        UserProfile updatedUserProfile = userProfileService.updateUserProfile(userProfileId, userProfile);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

    
    @DeleteMapping("/delete/{userProfileId}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long userProfileId) throws RuntimeException, UserProfileNotFoundException {
        userProfileService.deleteUserProfile(userProfileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}