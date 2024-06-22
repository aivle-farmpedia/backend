package com.farm.pedia.supportPolicy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farm.pedia.global.dto.response.PagedResponse;
import com.farm.pedia.supportPolicy.domain.SimpleSupportPolicy;
import com.farm.pedia.supportPolicy.domain.SupportPolicy;
import com.farm.pedia.supportPolicy.dto.response.SupportPolicyResponse;
import com.farm.pedia.supportPolicy.service.SupportPolicyService;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/support-policy")
public class SupportPolicyController {

	private final SupportPolicyService supportPolicyService;

	/**
	 * 지원정책 목록 조회
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * id : 지원정책 ID
	 * title : 제목
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PagedResponse<SimpleSupportPolicy> findAll(
		@Min(value = 1, message = "페이지 값은 최소 1이어야 합니다.") @RequestParam(defaultValue = "1") int page,
		@Min(value = 1, message = "크기 값은 최소 1이어야 합니다.") @RequestParam(defaultValue = "10") int size
	) {
		return supportPolicyService.findAll(page, size);
	}

	/**
	 * 지원정책 상세 조회
	 * @param policyId
	 * @return
	 * id : 지원정책 ID
	 * title : 제목
	 * content : 내용
	 * applyStart : 신청 시작일
	 * applyEnd : 신청 종료일
	 * chargeAgency : 담당기관
	 * eduTarget : 교육대상
	 * price : 금액
	 * infoUrl : 정보 URL
	 */
	@GetMapping("/{policyId}")
	@ResponseStatus(HttpStatus.OK)
	public SupportPolicyResponse findSupportPolicy(
		@PathVariable Long policyId
	) {
		SupportPolicy supportPolicy = supportPolicyService.findSupportPolicy(policyId);
		return SupportPolicyResponse.from(supportPolicy);
	}
}
