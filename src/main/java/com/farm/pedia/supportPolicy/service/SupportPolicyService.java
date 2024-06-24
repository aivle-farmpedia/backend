package com.farm.pedia.supportPolicy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farm.pedia.global.dto.response.PagedResponse;
import com.farm.pedia.supportPolicy.domain.Category;
import com.farm.pedia.supportPolicy.domain.SimpleSupportPolicy;
import com.farm.pedia.supportPolicy.domain.SupportPolicy;
import com.farm.pedia.supportPolicy.exception.exceptions.SupportPolicyNotFoundException;
import com.farm.pedia.supportPolicy.mapper.SupportPolicyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportPolicyService {

	private final SupportPolicyMapper supportPolicyMapper;

	public PagedResponse<SimpleSupportPolicy> findAll(int page, int size, Category category) {
		int offset = (page - 1) * size;
		String sort = category.getName();
		List<SimpleSupportPolicy> policies = supportPolicyMapper.findAll(size, offset, sort);
		return createPagedResponse(policies, page, size, sort);
	}

	public SupportPolicy findSupportPolicy(Long policyId) {
		return supportPolicyMapper.findById(policyId)
			.orElseThrow(SupportPolicyNotFoundException::new);
	}

	private PagedResponse<SimpleSupportPolicy> createPagedResponse(List<SimpleSupportPolicy> policies, int page,
		int size, String sort) {
		int totalElements = supportPolicyMapper.countAll(sort);
		int totalPages = (int)Math.ceil((double)totalElements / size);
		return PagedResponse.of(policies, page, size, totalElements, totalPages);
	}
}
