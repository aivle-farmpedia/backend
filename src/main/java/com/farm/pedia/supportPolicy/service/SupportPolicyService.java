package com.farm.pedia.supportPolicy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farm.pedia.global.dto.response.PagedResponse;
import com.farm.pedia.supportPolicy.domain.SimpleSupportPolicy;
import com.farm.pedia.supportPolicy.domain.SupportPolicy;
import com.farm.pedia.supportPolicy.mapper.SupportPolicyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportPolicyService {

	private final SupportPolicyMapper supportPolicyMapper;

	public PagedResponse<SimpleSupportPolicy> findAll(int page, int size) {
		int offset = (page - 1) * size;
		List<SimpleSupportPolicy> boards = supportPolicyMapper.findAll(size, offset);
		int totalElements = supportPolicyMapper.countAll();
		int totalPages = (int) Math.ceil((double) totalElements / size);

		return PagedResponse.of(boards, page, size, totalElements, totalPages);
	}

	public SupportPolicy findSupportPolicy(Long policyId) {
		return supportPolicyMapper.findById(policyId);
	}
}
