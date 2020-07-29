# Spring boot Mock service for providing data to api basicspringspockspringrest

This api is the endpoint which provides data to other api for spock framework testing.

# the data is loaded using the below class when the server starts

    package com.arun.mockservice.config;
    
    import com.arun.mockservice.model.Profile;
    import com.arun.mockservice.repository.ProfileRepository;
    import org.apache.commons.lang3.RandomStringUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.context.annotation.Configuration;
    
    import java.util.UUID;
    
    /**
     * @author arun on 7/28/20
     */
    @Configuration
    public class DataLoader implements CommandLineRunner {
    
        private ProfileRepository profileRepository;
    
        @Autowired
        public DataLoader(ProfileRepository profileRepository) {
            this.profileRepository = profileRepository;
        }
    
        @Override
        public void run(String... args) {
            loadData();
        }
    
        private void loadData() {
    
            for (int i = 0; i < 10; i++) {
                String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
                Profile profile = Profile.builder()
                        .email(email)
                        .uuid(UUID.randomUUID().toString())
                        .first_name(RandomStringUtils.randomAlphabetic(5))
                        .last_name(RandomStringUtils.randomAlphabetic(4))
                        .url(RandomStringUtils.randomAlphanumeric(6))
                        .tokenId(RandomStringUtils.randomNumeric(10))
                        .build();
                profileRepository.save(profile);
            }
    
        }
    
    
    }


Controllers provide end points to get the data which has been stored in the h2 database.

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


