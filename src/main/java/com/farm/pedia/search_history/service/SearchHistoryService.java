package com.farm.pedia.search_history.service;

import com.farm.pedia.search_history.domain.SearchHistory;
import com.farm.pedia.search_history.mapper.SearchHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private static final String REDIS_RECENT_SEARCH_KEY = "recentSearch:";

    private final SearchHistoryMapper searchHistoryMapper;
    private final RedisTemplate<String, String> redisTemplate;

    public void saveSearchKeyword(Long userId, String keyword) {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setUserId(userId);
        searchHistory.setKeyword(keyword);
        searchHistory.setSearchedAt(LocalDateTime.now());
        searchHistoryMapper.save(searchHistory);

        // Redis에 저장
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        redisTemplate.opsForZSet().add(redisKey, keyword, System.currentTimeMillis());
        redisTemplate.opsForZSet().removeRange(redisKey, 0, -6); // 최신 5개만 유지
    }

    public Set<String> getRecentSearchKeywords(Long userId) {
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        return redisTemplate.opsForZSet().reverseRange(redisKey, 0, 4);
    }

    public void deleteSearchKeyword(Long userId, String keyword) {
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        redisTemplate.opsForZSet().remove(redisKey, keyword);
    }
}