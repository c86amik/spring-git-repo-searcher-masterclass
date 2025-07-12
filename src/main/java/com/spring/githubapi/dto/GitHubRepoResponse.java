package com.spring.githubapi.dto;

import java.io.Serializable;
import java.time.Instant;

public class GitHubRepoResponse implements Serializable {

    private static final long serialVersionUID = 7707576683418425977L;

    private Long id;
    private String name;
    private String description;
    private String owner;
    private String language;
    private int stars;
    private int forks;
    private Instant lastUpdated;
}
