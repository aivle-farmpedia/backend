package com.farm.pedia.crop.domain;

import lombok.Getter;

@Getter
public class Variety {
	private final String name;
	private final String purpose;
	private final String skill;
	private final String imageUrl;

	private Variety(String name, String purpose, String skill, String imageUrl) {
		this.name = name;
		this.purpose = purpose;
		this.skill = skill;
		this.imageUrl = imageUrl;
	}
}