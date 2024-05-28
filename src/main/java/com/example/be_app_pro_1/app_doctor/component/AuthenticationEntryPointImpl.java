package com.example.be_app_pro_1.app_doctor.component;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		response.setContentType("application/json"); // Set JSON content type

		ObjectMapper mapper = new ObjectMapper(); // Use ObjectMapper for JSON serialization
		ErrorResponse errorResponse = new ErrorResponse("authentication_required", authException.getMessage());
		String jsonResponse = mapper.writeValueAsString(errorResponse);

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(jsonResponse);
	}
}
