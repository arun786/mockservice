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
