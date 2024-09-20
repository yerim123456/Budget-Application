package com.management.budget.exception.custom;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json; charset=UTF-8");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter().write("{\"error\": \"Forbidden\", \"message\": \"접근 권한이 없습니다.\"}");
	}

}
