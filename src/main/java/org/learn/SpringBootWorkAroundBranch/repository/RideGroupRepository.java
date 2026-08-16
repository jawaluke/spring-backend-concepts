package org.learn.SpringBootWorkAroundBranch.repository;

import org.learn.SpringBootWorkAroundBranch.model.RideGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideGroupRepository extends JpaRepository<RideGroup, Long> {

}
