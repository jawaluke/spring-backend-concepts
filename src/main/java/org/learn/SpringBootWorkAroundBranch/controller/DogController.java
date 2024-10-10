package org.learn.SpringBootWorkAroundBranch.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.learn.SpringBootWorkAroundBranch.entity.Dog;
import org.learn.SpringBootWorkAroundBranch.model.DogRequest;
import org.learn.SpringBootWorkAroundBranch.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DogController {

    private DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity getDogByBreed(@PathVariable @NotBlank String breed) {
        List<Dog> dogs = dogService.getDogsByBreed(breed);
        if(!dogs.isEmpty()) {
            return ResponseEntity.ok(dogs);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/dog")
    public ResponseEntity saveDogs(@RequestBody @Valid DogRequest dogRequest) {
        Dog dog = dogService.saveDog(dogRequest);
        if(dog != null) {
            return ResponseEntity.ok(dog);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/dog")
    public ResponseEntity getAllDogs() {
        List<Dog> dogs = dogService.getAllDogs();
        if(!dogs.isEmpty()) {
            return ResponseEntity.ok(dogs);
        }
        return ResponseEntity.notFound().build();
    }

}
