package org.learn.SpringBootWorkAroundBranch.service;

import jakarta.validation.Valid;
import org.learn.SpringBootWorkAroundBranch.entity.Dog;
import org.learn.SpringBootWorkAroundBranch.enums.DogGenderEnum;
import org.learn.SpringBootWorkAroundBranch.model.DogRequest;
import org.learn.SpringBootWorkAroundBranch.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    private DogRepository dogRepository;

    @Autowired
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<Dog> getDogsByBreed(String breed) {
        return dogRepository.findByDogBreed(breed);
    }

    public Dog saveDog(@Valid DogRequest dogRequest) {
        Assert.notNull(dogRequest, "Dog Request cannot be null");
        String gender = DogGenderEnum.getGender(dogRequest.getDogGender());
        Assert.notNull(gender, "Invalid gender type");
        Dog dog = Dog.builder()
                .dogName(dogRequest.getDogName())
                .dogBreed(dogRequest.getDogBreed())
                .dogGender(gender)
                .build();
        return dogRepository.save(dog);
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }
}
