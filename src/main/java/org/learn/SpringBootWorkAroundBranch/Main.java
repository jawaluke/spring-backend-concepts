package org.learn.SpringBootWorkAroundBranch;

import org.learn.SpringBootWorkAroundBranch.entity.Dog;
import org.learn.SpringBootWorkAroundBranch.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private DogRepository dogRepository;


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
//        dogRepository.save(
//                Dog.builder()
//                        .dogName("glo")
//                        .dogBreed("SPACE_DOG")
//                        .dogGender("FEMALE")
//                        .build()
//        );
//
//        System.out.println("dogRepository = " + dogRepository.findAll());
    }
}
