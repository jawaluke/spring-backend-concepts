package org.learn.SpringBootWorkAroundBranch.repository;

import org.learn.SpringBootWorkAroundBranch.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
