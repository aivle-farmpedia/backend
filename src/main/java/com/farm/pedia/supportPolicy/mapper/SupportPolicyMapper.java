package com.farm.pedia.supportPolicy.mapper;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.apache.ibatis.annotations.Mapper;

import com.farm.pedia.supportPolicy.domain.SimpleSupportPolicy;
import com.farm.pedia.supportPolicy.domain.SupportPolicy;

@Mapper
public interface SupportPolicyMapper {

	List<SimpleSupportPolicy> findAll(int limit, int offset);

	int countAll();

	Optional<SupportPolicy> findById(Long policyId);
}
