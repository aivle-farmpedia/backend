package com.farm.pedia.supportPolicy.domain;

import lombok.Getter;

@Getter
public class SimpleSupportPolicy {

	private final Long id;
	private final String title;

	private SimpleSupportPolicy(Long id, String title) {
		this.id = id;
		this.title = title;
	}
}
