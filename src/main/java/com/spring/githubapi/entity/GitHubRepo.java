package com.spring.githubapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "githubrepositories")
@Setter
@Getter
@Data
public class GitHubRepo {
    @Id
    private Long id;
    private String name;
    private String description;
    private String owner;
    private String language;
    private int stars;
    private int forks;
    private Instant lastUpdated;
}
