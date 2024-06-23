package com.farm.pedia.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Value("${api.server.url}")
	private String API_SERVER_URL;

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl(API_SERVER_URL)
			.defaultHeader("Content-Type", "application/json")
			.build();
	}
}