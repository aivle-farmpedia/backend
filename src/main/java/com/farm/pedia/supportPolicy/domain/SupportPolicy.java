package com.farm.pedia.supportPolicy.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class SupportPolicy {

	private final Long id;
	private final String title;
	private final String content;
	private final LocalDate applyStart;
	private final LocalDate applyEnd;
	private final String chargeAgency;
	private final String eduTarget;
	private final Integer price;
	private final String infoUrl;

	private SupportPolicy(Long id, String title, String content, LocalDate applyStart, LocalDate applyEnd, String chargeAgency, String eduTarget, Integer price, String infoUrl) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.applyStart = applyStart;
		this.applyEnd = applyEnd;
		this.chargeAgency = chargeAgency;
		this.eduTarget = eduTarget;
		this.price = price;
		this.infoUrl = infoUrl;
	}
}
