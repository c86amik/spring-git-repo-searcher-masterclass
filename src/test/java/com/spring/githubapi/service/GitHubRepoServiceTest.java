package com.spring.githubapi.service;

import com.spring.githubapi.dto.GitHubSearchRequest;
import com.spring.githubapi.entity.GitHubRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.List;

@SpringBootTest
public class GitHubRepoServiceTest {

    @Autowired
    private GitHubRepoService gitHubRepoService;

    @Test
    void testSearchAndSave() {
        GitHubSearchRequest request = new GitHubSearchRequest();
        request.setQuery("spring boot");
        request.setLanguage("Java");
        request.setSort("stars");
        List<GitHubRepo> gitHubRepos = gitHubRepoService.searchAndSave(request);
        assertFalse(gitHubRepos.isEmpty());
    }
}
