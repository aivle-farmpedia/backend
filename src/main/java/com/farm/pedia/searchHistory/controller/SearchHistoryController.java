package com.farm.pedia.searchHistory.controller;

import com.farm.pedia.auth.config.UserLogin;
import com.farm.pedia.searchHistory.service.SearchHistoryService;
import com.farm.pedia.user.domain.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Validated
public class SearchHistoryController {

    private final SearchHistoryService searchHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSearchKeyword(@UserLogin User user, @Valid @NotBlank @RequestParam String keyword) {
        searchHistoryService.saveSearchKeyword(user.getId(), keyword);
        log.info("User ID {}의 검색어 '{}'가 저장되었습니다.", user.getId(), keyword);
    }

    @GetMapping("/recent")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> getRecentSearchKeywords(@UserLogin User user) {
        Set<String> recentKeywords = searchHistoryService.getRecentSearchKeywords(user.getId());

        log.info("User ID {}의 최근 검색어를 조회하였습니다: {}", user.getId(), recentKeywords);
        return recentKeywords;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSearchKeyword(@UserLogin User user, @Valid @NotBlank @RequestParam String keyword) {
        searchHistoryService.deleteSearchKeyword(user.getId(), keyword);
        log.info("User ID {}의 검색어 '{}'가 삭제되었습니다.", user.getId(), keyword);
    }
}