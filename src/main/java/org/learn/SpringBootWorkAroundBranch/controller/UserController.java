package org.learn.SpringBootWorkAroundBranch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.SpringBootWorkAroundBranch.model.UserRequest;
import org.learn.SpringBootWorkAroundBranch.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final AppUserService appUserService;

    @PostMapping("/")
    public ResponseEntity createAppUser(@RequestBody UserRequest userRequest) {
        log.info("Request for creating app user : {}", userRequest.getUserName());
        return ResponseEntity.ok(appUserService.createUser(userRequest));
    }
}
