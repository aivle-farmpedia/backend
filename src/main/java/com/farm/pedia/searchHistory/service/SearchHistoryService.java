package com.farm.pedia.searchHistory.service;

import com.farm.pedia.searchHistory.domain.SearchHistory;
import com.farm.pedia.searchHistory.mapper.SearchHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private static final String REDIS_RECENT_SEARCH_KEY = "recentSearch:";

    private final SearchHistoryMapper searchHistoryMapper;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public void saveSearchKeyword(Long userId, String keyword) {
        SearchHistory searchHistory = SearchHistory.of(keyword, userId);
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