package com.farm.pedia.searchHistory.controller;

import com.farm.pedia.auth.config.UserLogin;
import com.farm.pedia.searchHistory.service.SearchHistoryService;
import com.farm.pedia.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchHistoryController {

    private final SearchHistoryService searchHistoryService;

    @PostMapping("/keyword")
    public void saveSearchKeyword(@UserLogin User user, @RequestBody String keyword) {
        searchHistoryService.saveSearchKeyword(user.getId(), keyword);
    }

    @GetMapping("/recent")
    public Set<String> getRecentSearchKeywords(@UserLogin User user) {
        return searchHistoryService.getRecentSearchKeywords(user.getId());
    }

    @DeleteMapping("/keyword")
    public void deleteSearchKeyword(@UserLogin User user, @RequestBody String keyword) {
        searchHistoryService.deleteSearchKeyword(user.getId(), keyword);
    }
}