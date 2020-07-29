package com.arun.mockservice.service;

import com.arun.mockservice.model.Profile;

import java.util.List;

/**
 * @author arun on 7/28/20
 */
public interface ProfileService {
    List<Profile> getProfiles();

    Profile getProfileByUuid(String uuid);
}
