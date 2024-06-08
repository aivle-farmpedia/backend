package com.farm.pedia.search_history.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchHistory {
    private Long id;
    private String keyword;
    private LocalDateTime searchedAt;
    private Long userId;
}