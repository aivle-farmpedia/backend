package com.farm.pedia.searchHistory.service;

import com.farm.pedia.searchHistory.domain.SearchResults;
import com.farm.pedia.searchHistory.mapper.SearchHistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchHistoryService {

    private static final String REDIS_RECENT_SEARCH_KEY = "recentSearch:";
    private final SearchHistoryMapper searchHistoryMapper;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    // 검색어 저장 (검색어 입력 시 즉시 저장)
    public void saveSearchKeyword(Long userId, String keyword) {
        // Redis 검색 키워드 저장
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        redisTemplate.opsForZSet().add(redisKey, keyword, System.currentTimeMillis());
        redisTemplate.opsForZSet().removeRange(redisKey, 0, -6); // 최신 5개
    }

    // 키워드 검색 수행
    public SearchResults performSearch(String keyword) {
        log.debug("키워드 [{}]의 검색 시작", keyword);

        // 품종 명으로 검색
        List<String> varietyNames = searchHistoryMapper.searchVarietyByName(keyword);

        // 일치하는 품종 명이 없을 시 카테고리로 검색
        if (varietyNames.isEmpty()) {
            List<String> cropsNames = searchHistoryMapper.searchCropsByVarietyName(keyword);
            log.debug("검색어와 일치하는 카테고리 : {}", cropsNames);
            return new SearchResults(cropsNames);
        }
        return new SearchResults(varietyNames);
    }

    // 최근 검색어
    public Set<String> getRecentSearchKeywords(Long userId) {
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        log.debug("Fetching recent search keywords for userId: {}", userId);
        return redisTemplate.opsForZSet().reverseRange(redisKey, 0, 4);
    }

    // 검색어 삭제
    public void deleteSearchKeyword(Long userId, String keyword) {
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        log.debug("Deleting search keyword: {} for userId: {}", keyword, userId);
        redisTemplate.opsForZSet().remove(redisKey, keyword);
    }
}