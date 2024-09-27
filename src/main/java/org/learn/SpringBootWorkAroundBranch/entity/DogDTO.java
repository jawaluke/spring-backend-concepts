package org.learn.SpringBootWorkAroundBranch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.learn.SpringBootWorkAroundBranch.model.DogBreed;
import org.learn.SpringBootWorkAroundBranch.model.Gender;

import java.util.List;
import java.util.Set;

@Table(name = "Dog", schema = "Dog_House")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ids", unique = true)
    private int id;

    @Column(name = "DogName")
    private String name;

    @Column(name = "DogBreed")
    @Enumerated(value = EnumType.STRING)
    private DogBreed breed;

    @Column(name = "DogGender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "dogId", cascade = CascadeType.ALL)
    private Set<OwnerDTO> ownerDTOList;
}
