package com.spring.githubapi.repository;

import com.spring.githubapi.entity.GitHubRepo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GitHubRepository extends JpaRepository<GitHubRepo, Long> {
    List<GitHubRepo> findAll(Specification<GitHubRepo> spec, Sort sort);
}
