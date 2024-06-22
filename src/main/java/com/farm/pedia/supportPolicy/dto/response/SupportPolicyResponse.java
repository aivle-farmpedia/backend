package com.farm.pedia.supportPolicy.dto.response;

import java.time.LocalDate;

import com.farm.pedia.supportPolicy.domain.SupportPolicy;

import lombok.Getter;

@Getter
public class SupportPolicyResponse {

	private final Long id;
	private final String title;
	private final String content;
	private final LocalDate applyStart;
	private final LocalDate applyEnd;
	private final String chargeAgency;
	private final String eduTarget;
	private final int price;
	private final String infoUrl;

	private SupportPolicyResponse(Long id, String title, String content, LocalDate applyStart, LocalDate applyEnd,
		String chargeAgency, String eduTarget, int price, String infoUrl) {
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

	public static SupportPolicyResponse from(SupportPolicy supportPolicy) {
		return new SupportPolicyResponse(supportPolicy.getId(), supportPolicy.getTitle(), supportPolicy.getContent(),
			supportPolicy.getApplyStart(), supportPolicy.getApplyEnd(), supportPolicy.getChargeAgency(),
			supportPolicy.getEduTarget(), supportPolicy.getPrice(), supportPolicy.getInfoUrl());
	}
}
