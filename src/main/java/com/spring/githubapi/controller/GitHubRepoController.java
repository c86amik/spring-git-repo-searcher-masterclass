package com.spring.githubapi.controller;

import com.spring.githubapi.dto.GitHubSearchRequest;
import com.spring.githubapi.entity.GitHubRepo;
import com.spring.githubapi.service.GitHubRepoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
@Slf4j
public class GitHubRepoController {

    @Autowired
    private GitHubRepoService gitHubRepoService;

    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> searchRepos(@RequestBody GitHubSearchRequest request) {
        List<GitHubRepo> gitHubRepos = gitHubRepoService.searchAndSave(request);
        log.info("GitHub Repos Results : {}", gitHubRepos);
        return ResponseEntity.ok(Map.of("message", "Repositories fetched and saved successfully",
                "repositories", gitHubRepos));
    }

    @GetMapping("/repositories")
    public ResponseEntity<Map<String, Object>> getRepos(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minStars,
            @RequestParam(required = false, defaultValue = "stars") String sort) {
        List<GitHubRepo> gitHubRepos = gitHubRepoService.filterGitHubRepos(language, minStars, sort);
        log.info("GitHub Repos fetched from PostGre : {}", gitHubRepos);
        return ResponseEntity.ok(Map.of("repositories", gitHubRepos));
    }
}
