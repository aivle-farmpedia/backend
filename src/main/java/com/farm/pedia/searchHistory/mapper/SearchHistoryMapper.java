package com.farm.pedia.searchHistory.mapper;

import com.farm.pedia.searchHistory.domain.SearchHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchHistoryMapper {

    void save(SearchHistory searchHistory);

    List<String> searchVarietyByName(@Param("keyword") String keyword);

    List<String> searchCropsByVarietyName(@Param("keyword") String keyword);
}