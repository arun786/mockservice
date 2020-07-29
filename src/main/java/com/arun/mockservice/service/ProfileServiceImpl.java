package com.arun.mockservice.service;

import com.arun.mockservice.model.Profile;
import com.arun.mockservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arun on 7/28/20
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> getProfiles() {
        Iterable<Profile> all = profileRepository.findAll();
        List<Profile> profiles = new ArrayList<>();
        all.forEach(profiles::add);
        return profiles;
    }

    @Override
    public Profile getProfileByUuid(String uuid) {
        return profileRepository.getByUuid(uuid);
    }
}
