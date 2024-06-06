package com.farm.pedia.auth.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.farm.pedia.user.domain.User;
import com.farm.pedia.user.service.UserService;

public class UserLoginResolver implements HandlerMethodArgumentResolver {

	private final UserService userService;

	public UserLoginResolver(final UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(UserLogin.class);
	}

	@Override
	public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		String uuid = webRequest.getHeader("Authorization");

		return userService.findByUuid(uuid);
	}
}