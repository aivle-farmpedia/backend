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

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Validated
public class SearchHistoryController {

    private final SearchHistoryService searchHistoryService;

    // 자동 완성
    @GetMapping("/autocomplete")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> autocomplete(@RequestParam("keyword") String keyword) {
		return searchHistoryService.autocomplete(keyword);
    }
    // 자동완성 리스트 내 항목 이름 저장 & 상세 정보 가져오기
    @PostMapping("/select")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> selectAndSave(@UserLogin User user, @Valid @NotBlank(message = "검색은 2글자 이상만 가능합니다.") @RequestParam("name") String name) {
        Map<String, Object> result = searchHistoryService.getDetailsAndSave(user.getId(), name);
        log.info("선택한 항목의 상세 정보: {}", result);
        return result;
    }


    // 검색 & 상세 정보 가져오기
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> searchAndSave(@UserLogin User user, @Valid @NotBlank(message = "검색은 2글자 이상만 가능합니다.") String keyword) {
        // 검색어 저장
        searchHistoryService.saveSearchKeyword(user.getId(), keyword);

        // 검색 실행
        List<Map<String, Object>> results = searchHistoryService.search(keyword);

        log.info("검색 결과: {}", results);
        return results;
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
