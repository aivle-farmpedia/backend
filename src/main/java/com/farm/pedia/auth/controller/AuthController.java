package com.farm.pedia.auth.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.pedia.user.dto.UserSaveRequest;
import com.farm.pedia.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@PostMapping("/save")
	public ResponseEntity<Void> saveUser(@RequestBody UserSaveRequest userSaveRequest) {
		userService.save(userSaveRequest.getUuid());
		return ResponseEntity.status(CREATED).build();
	}
}
