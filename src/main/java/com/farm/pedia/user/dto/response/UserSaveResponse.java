package com.farm.pedia.user.dto.response;

import com.farm.pedia.user.domain.User;

import lombok.Getter;

@Getter
public class UserSaveResponse {

	private final Long id;

	private UserSaveResponse(Long id) {
		this.id = id;
	}

	public static UserSaveResponse from(User user) {
		return new UserSaveResponse(user.getId());
	}
}
