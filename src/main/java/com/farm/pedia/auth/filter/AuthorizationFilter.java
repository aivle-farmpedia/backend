package com.farm.pedia.auth.filter;

import static org.springframework.http.HttpHeaders.*;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {

	private static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;

		String requestURI = request.getRequestURI();

		if (isExcludedURI(requestURI)) {
			filterChain.doFilter(request, response);
			return;
		}

		String uuid = request.getHeader(AUTHORIZATION);

		if (uuid == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "uuid가 존재하지 않습니다");
			return;
		}

		if (!uuid.matches(UUID_PATTERN)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "uuid가 유효하지 않습니다.");
			return;
		}
		filterChain.doFilter(request, response);
	}

	private boolean isExcludedURI(String requestURI) {
		return requestURI.startsWith("/api/auth") || requestURI.startsWith("/privacy") || requestURI.startsWith("/download");
	}
}
