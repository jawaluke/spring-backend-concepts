package org.learn.SpringBootWorkAroundBranch.repository;

import org.learn.SpringBootWorkAroundBranch.entity.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<ShortenUrl ,String> {
}
