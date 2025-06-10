package com.bni.bni.controller;

import com.bni.bni.entity.Profile;
import com.bni.bni.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    // Get all profiles
    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    // Get profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        return profile.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get profile by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable Long userId) {
        Profile profile = profileRepository.findByUserId(userId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new profile
    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        profile.setCreatedAt(ZonedDateTime.now());
        profile.setUpdatedAt(ZonedDateTime.now());
        return profileRepository.save(profile);
    }

    // Update profile
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile updatedProfile) {
        return profileRepository.findById(id)
                .map(profile -> {
                    profile.setFirstName(updatedProfile.getFirstName());
                    profile.setLastName(updatedProfile.getLastName());
                    profile.setPlaceOfBirth(updatedProfile.getPlaceOfBirth());
                    profile.setDateOfBirth(updatedProfile.getDateOfBirth());
                    profile.setUpdatedAt(ZonedDateTime.now());
                    return ResponseEntity.ok(profileRepository.save(profile));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete profile
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}