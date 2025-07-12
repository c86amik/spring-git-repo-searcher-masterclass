package com.spring.githubapi.service;

import com.spring.githubapi.dto.GitHubSearchRequest;
import com.spring.githubapi.entity.GitHubRepo;
import com.spring.githubapi.repository.GitHubRepository;
import com.spring.githubapi.util.GitHubRepoConstants;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GitHubRepoService {

    @Autowired
    private GitHubRepository gitHubRepository;
    @Autowired
    private GitHubApiRepoClient gitHubApiRepoClient;

    public List<GitHubRepo> searchAndSave(GitHubSearchRequest gitHubSearchRequest) {
        List<GitHubRepo> gitHubRepos = gitHubApiRepoClient.searchGitHubRepos(gitHubSearchRequest.getQuery(),
                gitHubSearchRequest.getLanguage(), gitHubSearchRequest.getSort());
        log.info("GitHub API Search Response : {}", gitHubRepos);
        for (GitHubRepo gitHubRepo : gitHubRepos) {
            gitHubRepository.save(gitHubRepo);
        }
        return  gitHubRepos;
    }

    public List<GitHubRepo> filterGitHubRepos(String language, Integer minStars, String sortBy) {
        Specification<GitHubRepo> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != language) {
                predicates.add(cb.equal(root.get(GitHubRepoConstants.LANGUAGE), language));
            }
            if (null != minStars) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(GitHubRepoConstants.STARS), minStars));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return gitHubRepository.findAll(spec, Sort.by(Sort.Direction.DESC,
                null != sortBy ? sortBy : GitHubRepoConstants.STARS));
    }
}
