package org.learn.SpringBootWorkAroundBranch.repository;

import org.learn.SpringBootWorkAroundBranch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getByUsername(String username);
}
