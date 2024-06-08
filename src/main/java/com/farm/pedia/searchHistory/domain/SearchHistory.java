package com.farm.pedia.searchHistory.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchHistory {
	private final Long id;
	private final String keyword;
	private final LocalDateTime searchedAt;
	private final Long userId;

	private SearchHistory(Long id, String keyword, LocalDateTime searchedAt, Long userId) {
		this.id = id;
		this.keyword = keyword;
		this.searchedAt = searchedAt;
		this.userId = userId;
	}

	public static SearchHistory of(String keyword, Long userId) {
		return new SearchHistory(null, keyword, LocalDateTime.now(), userId);
	}
}