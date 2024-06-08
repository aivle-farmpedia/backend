package com.farm.pedia.searchHistory.mapper;

import com.farm.pedia.searchHistory.domain.SearchHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SearchHistoryMapper {

    @Insert("INSERT INTO search_history (keyword, searched_at, user_id) VALUES (#{keyword}, #{searchedAt}, #{userId})")
    void save(SearchHistory searchHistory);

    @Select("SELECT * FROM search_history WHERE user_id = #{userId} ORDER BY searched_at DESC LIMIT 5")
    List<SearchHistory> findRecentByUserId(Long userId);

    @Delete("DELETE FROM search_history WHERE id = #{id} AND user_id = #{userId}")
    void deleteByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
}