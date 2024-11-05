package org.learn.SpringBootWorkAroundBranch.controller;

import lombok.extern.slf4j.Slf4j;
import org.learn.SpringBootWorkAroundBranch.facade.UserLoginFacade;
import org.learn.SpringBootWorkAroundBranch.model.User;
import org.learn.SpringBootWorkAroundBranch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/free")
@RestController
@Slf4j
public class BaseController {

    private final UserLoginFacade userLoginFacade;

    @Autowired
    public BaseController(UserLoginFacade userLoginFacade) {
        this.userLoginFacade = userLoginFacade;
    }

    @GetMapping("/info")
    public String getInfo() {
        return "Work around branch";
    }

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody User user) {
        try {
            Objects.requireNonNull(
                    userLoginFacade.userSignIn(user),
                    "Error saving user");
            return ResponseEntity.ok("User saved");
        }
        catch (Exception e) {
            // Log the error and return an appropriate response
            log.error("Error saving user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        String tokekn = userLoginFacade.userLogin(user);
        return ResponseEntity.ok(tokekn);
    }

}
