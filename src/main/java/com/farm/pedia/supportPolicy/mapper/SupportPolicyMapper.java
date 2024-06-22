package com.farm.pedia.supportPolicy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.farm.pedia.supportPolicy.domain.SimpleSupportPolicy;
import com.farm.pedia.supportPolicy.domain.SupportPolicy;

@Mapper
public interface SupportPolicyMapper {

	List<SimpleSupportPolicy> findAll(int limit, int offset);

	int countAll();

	SupportPolicy findById(Long policyId);
}
