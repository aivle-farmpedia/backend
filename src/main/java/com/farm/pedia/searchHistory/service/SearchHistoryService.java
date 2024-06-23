package com.farm.pedia.searchHistory.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchHistoryService {
    void saveSearchKeyword(Long userId, String keyword);
    List<Map<String, Object>> autocomplete(String keyword);
    List<Map<String, Object>> search(String keyword);
    Map<String, Object> getDetailsAndSave(Long userId, String name);
    Set<String> getRecentSearchKeywords(Long userId);
    void deleteSearchKeyword(Long userId, String keyword);
}