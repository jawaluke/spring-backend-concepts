package org.learn.SpringBootWorkAroundBranch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "dog_house", name = "dog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ids")
    int id;

    @Column(name = "dog_name")
    String dogName;

    @Column(name = "dog_breed")
    String dogBreed;

    @Column(name = "dog_gender")
    String dogGender;
}
