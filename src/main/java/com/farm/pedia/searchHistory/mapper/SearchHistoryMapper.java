package com.farm.pedia.searchHistory.mapper;

import com.farm.pedia.searchHistory.domain.SearchHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearchHistoryMapper {

    void save(SearchHistory searchHistory);

    // 자동 완성 - id, name만 return
    List<Map<String, Object>> searchVarietyDetails(@Param("keyword") String keyword);
    List<Map<String, Object>> searchCropsDetails(@Param("keyword") String keyword);

    // 검색 버튼 시 (백과사전) - id, name, cropsname(category), imageUrl
    List<Map<String, Object>> searchVarietyName(@Param("keyword") String keyword);
    List<Map<String, Object>> searchCropsName(@Param("keyword") String keyword);

    // 자동완성 리스트 내 항목 클릭 - id, name, cropsname(category), imageUrl
    Map<String, Object> getVarietyDetailsByName(@Param("name") String name);
    Map<String, Object> getCropsDetailsByName(@Param("name") String name);
}
