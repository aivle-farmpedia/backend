package com.farm.pedia.auth.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farm.pedia.user.domain.User;
import com.farm.pedia.auth.dto.request.UserSaveRequest;
import com.farm.pedia.auth.dto.response.UserSaveResponse;
import com.farm.pedia.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@PostMapping("/save")
	@ResponseStatus(CREATED)
	public UserSaveResponse saveUser(@RequestBody UserSaveRequest userSaveRequest) {
		userService.save(userSaveRequest.getUuid());

		User user = userService.findByUuid(userSaveRequest.getUuid());
		return UserSaveResponse.from(user);
	}
}
