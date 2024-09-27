package org.learn.SpringBootWorkAroundBranch.service;

import org.learn.SpringBootWorkAroundBranch.entity.DogDTO;
import org.learn.SpringBootWorkAroundBranch.entity.OwnerDTO;
import org.learn.SpringBootWorkAroundBranch.model.Dog;
import org.learn.SpringBootWorkAroundBranch.model.DogBreed;
import org.learn.SpringBootWorkAroundBranch.model.Gender;
import org.learn.SpringBootWorkAroundBranch.repository.DogRepository;
import org.learn.SpringBootWorkAroundBranch.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BaseService {

    private DogRepository dogRepository;

    private OwnerRepository ownerRepository;

    @Autowired
    public BaseService(DogRepository dogRepository, OwnerRepository ownerRepository) {
        this.dogRepository = dogRepository;
        this.ownerRepository = ownerRepository;
    }

    @Transactional
    public List<String> getRaiders(int no) {
        List<String> arr = Arrays.asList("apple","orange","pineapple","banana");
        if(no==5) {
            throw new RuntimeException("Breaking very bad");
        }
        return arr;
    }

    @Transactional
    public DogDTO saveDog(Dog dog, int n) {
        DogDTO dogDTO = ConvertDogToDTO(dog);
        doException(n);
        DogDTO saved = dogRepository.save(dogDTO);
        doException2(n);
        return saved;
    }

    public void doException(int n) {
        if(n==5) {
            throw new RuntimeException("Breaking very bad");
        }
    }

    public void doException2(int n) {
        if(n==6) {
            throw new RuntimeException("Breaking very bad2");
        }
    }

    private DogDTO ConvertDogToDTO(Dog dog) {
        DogDTO dogDTO = new DogDTO();
        dogDTO.setName(dog.getName());
        dogDTO.setBreed(DogBreed.getBreed(dog.getBreed()));
        dogDTO.setGender(Gender.getGender(dog.getGender()));
        return dogDTO;
    }

    public List<DogDTO> getDog(int id) {
        return dogRepository.findAllById(Collections.singleton(id));
    }

    public List<OwnerDTO> getOwner(int id) {
       return ownerRepository.findAllByIds(Collections.singleton(id));
    }
}
