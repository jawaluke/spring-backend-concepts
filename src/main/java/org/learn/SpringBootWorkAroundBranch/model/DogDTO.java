package org.learn.SpringBootWorkAroundBranch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Dog", schema = "DogHouse")
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
}
