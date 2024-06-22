package com.farm.pedia.searchHistory.controller;

import com.farm.pedia.auth.config.UserLogin;
import com.farm.pedia.searchHistory.domain.SearchResults;
import com.farm.pedia.searchHistory.service.SearchHistoryService;
import com.farm.pedia.user.domain.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveAndPerformSearch(@UserLogin User user, @Valid @NotBlank @RequestParam("keyword") String keyword) {
        // 검색어 저장
        searchHistoryService.saveSearchKeyword(user.getId(), keyword);
        log.info("UserID '{}'의 검색어 [{}] 저장되었습니다.", user.getId(), keyword);

        // 검색 실행
        SearchResults results = searchHistoryService.performSearch(keyword);
        if (results.getNames().isEmpty()) {
            String message = "검색어 [" + keyword + "]은 존재하지 않습니다.";
            log.info(message);
            return new ResponseEntity<>(message, HttpStatus.OK);

        }
        String formattedResults = "연관 검색어 : " + String.join(", ", results.getNames());
        log.info("{}", formattedResults);
        return new ResponseEntity<>(formattedResults, HttpStatus.OK);
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
