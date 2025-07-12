package com.spring.githubapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.githubapi.entity.GitHubRepo;
import com.spring.githubapi.util.GitHubRepoConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GitHubApiRepoClient {


    private final RestTemplate restTemplate = new RestTemplate();

    public List<GitHubRepo> searchGitHubRepos(String query, String language, String sort) {
        String url = UriComponentsBuilder.fromUriString(GitHubRepoConstants.GIT_HUB_REPO_URL)
                .queryParam(GitHubRepoConstants.Q, query + (language != null ? "+" +
                        GitHubRepoConstants.LANGUAGE + ":" + language : GitHubRepoConstants.EMPTY_STRING))
                .queryParam(GitHubRepoConstants.SORT, sort != null ? sort : GitHubRepoConstants.STARS)
                .queryParam(GitHubRepoConstants.ORDER, GitHubRepoConstants.DESC)
                .toUriString();
        log.info("GitHub Search API URL : {}", url);
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(GitHubRepoConstants.GIT_API_ERROR);
        }
        log.info("GitHub API response objects : {}", response.getBody().get(GitHubRepoConstants.ITEMS));
        List<GitHubRepo> gitHubRepos = new ArrayList<>();
        for (JsonNode item : response.getBody().get(GitHubRepoConstants.ITEMS)) {
            GitHubRepo gitHubRepo = new GitHubRepo();
            gitHubRepo.setId(item.get(GitHubRepoConstants.ID).asLong());
            gitHubRepo.setName(item.get(GitHubRepoConstants.NAME).asText());
            gitHubRepo.setDescription(item.get(GitHubRepoConstants.DESCRIPTION).asText(null));
            gitHubRepo.setOwner(item.get(GitHubRepoConstants.OWNER).get(GitHubRepoConstants.LOGIN).asText());
            gitHubRepo.setLanguage(item.get(GitHubRepoConstants.LANGUAGE).asText(null));
            gitHubRepo.setStars(item.get(GitHubRepoConstants.STARGAZERS_COUNT).asInt());
            gitHubRepo.setForks(item.get(GitHubRepoConstants.FORKS_COUNT).asInt());
            gitHubRepo.setLastUpdated(Instant.parse(item.get(GitHubRepoConstants.UPDATED_AT).asText()));
            gitHubRepos.add(gitHubRepo);
        }
        return gitHubRepos;
    }
}
