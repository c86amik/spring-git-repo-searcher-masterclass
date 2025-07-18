package com.spring.githubapi.exception;

import com.spring.githubapi.util.GitHubRepoConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAll(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(GitHubRepoConstants.ERROR, e.getMessage()));
    }
}
