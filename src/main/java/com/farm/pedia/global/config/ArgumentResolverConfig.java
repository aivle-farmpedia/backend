package com.farm.pedia.global.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.farm.pedia.auth.config.UserLoginResolver;
import com.farm.pedia.user.service.UserService;

@Configuration
public class ArgumentResolverConfig implements WebMvcConfigurer {

	private final UserService userService;

	public ArgumentResolverConfig(final UserService userService) {
		this.userService = userService;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(userLoginResolver());
	}

	@Bean
	public UserLoginResolver userLoginResolver() {
		return new UserLoginResolver(userService);
	}
}