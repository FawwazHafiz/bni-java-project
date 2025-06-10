package com.bni.bni.service;

import com.bni.bni.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<Profile> getAllProfiles();
    Optional<Profile> getProfileById(Long id);
    Profile getProfileByUserId(Long userId);
    Profile createProfile(Profile profile);
    Optional<Profile> updateProfile(Long id, Profile profile);
    boolean deleteProfile(Long id);
}