package com.farm.pedia.searchHistory.impl;

import com.farm.pedia.searchHistory.mapper.SearchHistoryMapper;
import com.farm.pedia.searchHistory.service.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchHistoryServiceImpl implements SearchHistoryService {

    private static final String REDIS_RECENT_SEARCH_KEY = "recentSearch:";
    private final SearchHistoryMapper searchHistoryMapper;
    private final RedisTemplate<String, String> redisTemplate;
    SearchHistoryService self;

    @Transactional
    @Override
    public void saveSearchKeyword(Long userId, String keyword) {
        // Redis 검색 키워드 저장
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        redisTemplate.opsForZSet().add(redisKey, keyword, System.currentTimeMillis());
        redisTemplate.opsForZSet().removeRange(redisKey, 0, -6); // 최신 5개
    }

    @Transactional(readOnly = true)
    @Override
    public List<Map<String, Object>> autocomplete(String keyword) {
        List<Map<String, Object>> varietyNames = searchHistoryMapper.searchVarietyName(keyword);
        List<Map<String, Object>> cropNames = searchHistoryMapper.searchCropsName(keyword);
        varietyNames.addAll(cropNames);
        varietyNames.sort(Comparator.comparing(map -> (String) map.get("name")));
        return varietyNames;
    }

    @Transactional
    @Override
    public Map<String, Object> getDetailsAndSave(Long userId, String name) {
        // 검색어 저장

        self.saveSearchKeyword(userId, name);

        // variety 테이블에서 검색
        Map<String, Object> varietyDetails = searchHistoryMapper.getVarietyDetailsByName(name);
        if (!varietyDetails.isEmpty()) {
            return varietyDetails;
        }

        // crops 테이블에서 검색
        Map<String, Object> cropsDetails = searchHistoryMapper.getCropsDetailsByName(name);
        if (!cropsDetails.isEmpty()) {
            return cropsDetails;
        }
        return Map.of();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Map<String, Object>> search(String keyword) {
        List<Map<String, Object>> varietyNames = searchHistoryMapper.searchVarietyDetails(keyword);
        List<Map<String, Object>> cropNames = searchHistoryMapper.searchCropsDetails(keyword);
        varietyNames.addAll(cropNames);
        varietyNames.sort(Comparator.comparing(map -> (String) map.get("name")));
        return varietyNames;
    }

    @Override
    public Set<String> getRecentSearchKeywords(Long userId) {
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        log.debug("Fetching recent search keywords for userId: {}", userId);
        return redisTemplate.opsForZSet().reverseRange(redisKey, 0, 4);
    }

    @Override
    public void deleteSearchKeyword(Long userId, String keyword) {
        String redisKey = REDIS_RECENT_SEARCH_KEY + userId;
        log.debug("Deleting search keyword: {} for userId: {}", keyword, userId);
        redisTemplate.opsForZSet().remove(redisKey, keyword);
    }
}