package com.example.be_app_pro_1.app_doctor.component;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		response.setContentType("application/json"); // Set JSON content type

		ObjectMapper mapper = new ObjectMapper(); // Use ObjectMapper for JSON serialization
		ErrorResponse errorResponse = new ErrorResponse("AccessDeniedHandlerImpl2", accessDeniedException.getMessage());
		String jsonResponse = mapper.writeValueAsString(errorResponse);

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter().write(jsonResponse);
	}
}
