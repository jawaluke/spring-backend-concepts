package org.learn.SpringBootWorkAroundBranch.facade;

import org.learn.SpringBootWorkAroundBranch.entity.UserEntity;
import org.learn.SpringBootWorkAroundBranch.model.User;
import org.learn.SpringBootWorkAroundBranch.service.UserService;
import org.learn.SpringBootWorkAroundBranch.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Objects;

@Service
public class UserLoginFacade {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserLoginFacade(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public UserEntity userSignIn(User user) {
        //validate
        //password hash
        //DB save
        String salt = generateRandomSalt();
        Objects.requireNonNullElse(salt, "offset");
        String hashedpassword = passwordEncoder.encode(salt+user.getPassword());
        return userService.saveUser(
                        user.getUsername(),
                        hashedpassword,
                        salt
        );

    }

    public String userLogin(User user) {
        boolean match = userService.validateUser(user.getUsername(), user.getPassword());
        if(!match) throw new RuntimeException("Password is incorrect");
        return generateJwt(user.getUsername(), user.getPassword());
    }

    private String generateJwt(String username, String password) {
        String token = jwtUtil.generateJwt(username, password);
        Objects.requireNonNull(token, "token is null");
        return token;
    }


    private String generateRandomSalt() {
        byte[] ran = new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ran);
        return new String(ran);
    }


}
