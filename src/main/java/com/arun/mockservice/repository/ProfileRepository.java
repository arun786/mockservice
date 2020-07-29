package com.arun.mockservice.repository;

import com.arun.mockservice.model.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author arun on 7/28/20
 */
public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {
    Profile getByUuid(String uuid);
}
