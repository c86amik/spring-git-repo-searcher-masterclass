package com.spring.githubapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@Data
public class GitHubSearchRequest implements Serializable {

    private static final long serialVersionUID = 7707576683418425977L;

    private String query;
    private String language;
    private String sort; // stars, forks, updated
}
