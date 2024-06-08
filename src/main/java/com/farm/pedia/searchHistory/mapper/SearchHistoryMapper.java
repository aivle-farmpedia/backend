package com.farm.pedia.searchHistory.mapper;

import com.farm.pedia.searchHistory.domain.SearchHistory;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SearchHistoryMapper {

    void save(SearchHistory searchHistory);
}