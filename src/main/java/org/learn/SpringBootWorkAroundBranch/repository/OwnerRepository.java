package org.learn.SpringBootWorkAroundBranch.repository;

import org.learn.SpringBootWorkAroundBranch.entity.OwnerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerDTO, Integer> {

    @Query("SELECT a FROM Owner a LEFT JOIN FETCH  a.Dog")
    List<OwnerDTO> findAllByIds(Integer singleton);
}
