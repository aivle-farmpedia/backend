package com.farm.pedia.global.dto.response;

import java.util.List;

import lombok.Getter;

@Getter
public class PagedResponse<T> {

	private final List<T> data;
	private final int page;
	private final int size;
	private final long totalElements;
	private final int totalPages;

	private PagedResponse(List<T> data, int page, int size, long totalElements, int totalPages) {
		this.data = data;
		this.page = page;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	public static <T> PagedResponse<T> of(List<T> data, int page, int size, long totalElements, int totalPages) {
		return new PagedResponse<>(data, page, size, totalElements, totalPages);
	}
}
