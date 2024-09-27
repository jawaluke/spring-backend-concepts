package org.learn.SpringBootWorkAroundBranch.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Owner", schema = "Dog_House")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    @Id
    @Column(name = "ownerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerId;

    @Column(name = "ownername")
    private String ownerName;

    @Column(name = "ownercity")
    private String ownerCity;

    @Column(name = "ownerage")
    private int ownerAge;

    @ManyToOne
    @JoinColumn(name = "dogid")
    private DogDTO dogId;


}
