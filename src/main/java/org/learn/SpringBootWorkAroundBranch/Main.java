package org.learn.SpringBootWorkAroundBranch;

import lombok.extern.slf4j.Slf4j;
import org.learn.SpringBootWorkAroundBranch.entity.UserEntity;
import org.learn.SpringBootWorkAroundBranch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootApplication(exclude = {})
public class Main implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.run(args);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        log.info("saving users");
        String pass = passwordEncoder.encode("salty "+"admin");
//        userRepository.save(
//                UserEntity.builder()
//                        .username("admin")
//                        .password(pass)
//                        .salt("salty")
//                        .build()
//        );
    }
}
