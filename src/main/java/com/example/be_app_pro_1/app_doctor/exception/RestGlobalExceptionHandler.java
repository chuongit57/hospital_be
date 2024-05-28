//package com.example.be_app_pro_1.app_doctor.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ProblemDetail;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.example.be_app_pro_1.app_doctor.component.ErrorResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@RestControllerAdvice
//public class RestGlobalExceptionHandler {
//
//	@ExceptionHandler(.class)
//	public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
//		ErrorResponse errorResponse = new ErrorResponse("authentication_required");
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//}
