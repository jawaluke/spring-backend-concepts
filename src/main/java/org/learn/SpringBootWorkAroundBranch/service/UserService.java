package org.learn.SpringBootWorkAroundBranch.service;

import org.learn.SpringBootWorkAroundBranch.entity.UserEntity;
import org.learn.SpringBootWorkAroundBranch.model.User;
import org.learn.SpringBootWorkAroundBranch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Objects;

@Service
@DependsOn("passwordEncoder")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Transactional
    public UserEntity saveUser(String username, String hashedpassword, String salt) {
        return userRepository.save(
                UserEntity.builder()
                        .username(username)
                        .password(hashedpassword)
                        .salt(salt)
                        .build()
        );
    }

    private String generateSalt() {
        byte[] bytes = new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return new String(bytes);
    }

    public UserEntity getUserByName(String username) {
        return userRepository.getByUsername(username);
    }

    public boolean validateUser(String username, String password) {
        UserEntity userRecord = getUserByName(username);
        Objects.requireNonNull(userRecord, "User not exists");
        return passwordEncoder.matches(userRecord.getSalt()+password, userRecord.getPassword());
    }
}
