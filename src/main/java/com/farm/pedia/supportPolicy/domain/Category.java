package com.farm.pedia.supportPolicy.domain;

import lombok.Getter;

@Getter
public enum Category {

	ALL("전체"),
	EDUCATION("교육"),
	BUSINESS("사업");

	private final String name;

	Category(String name) {
		this.name = name;
	}
}
