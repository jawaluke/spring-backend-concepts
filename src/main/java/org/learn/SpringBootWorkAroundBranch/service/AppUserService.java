package org.learn.SpringBootWorkAroundBranch.service;

import lombok.RequiredArgsConstructor;
import org.learn.SpringBootWorkAroundBranch.model.AppUser;
import org.learn.SpringBootWorkAroundBranch.model.UserRequest;
import org.learn.SpringBootWorkAroundBranch.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public boolean isExist(Long userId) {
        return appUserRepository.existsById(userId);
    }

    public AppUser createUser(UserRequest userRequest) {
        return appUserRepository.save(AppUser.builder()
                        .userName(userRequest.getUserName())
                .build());
    }
}
