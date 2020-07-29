package com.arun.mockservice.controller;

import com.arun.mockservice.model.Profile;
import com.arun.mockservice.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author arun on 7/28/20
 */
@RestController
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/v1/profiles")
    public ResponseEntity<List<Profile>> getAllProfile() {
        log.info("we are in mock service.....");
        List<Profile> profiles = profileService.getProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/v1/profiles/{uuid}")
    public ResponseEntity<Profile> getProfile(@PathVariable String uuid) {
        Profile profile = profileService.getProfileByUuid(uuid);
        return ResponseEntity.ok(profile);
    }
}
