package com.farm.pedia.supportPolicy.exception.exceptions;

public class SupportPolicyNotFoundException extends RuntimeException {

	public SupportPolicyNotFoundException() {
		super("해당 지원정책을 찾을 수 없습니다.");
	}
}
