package com.example.be_app_pro_1.app_doctor.controller.auth;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant.ApiPath;
import com.example.be_app_pro_1.app_doctor.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = ApiPath.API + ApiPath.AUTH)
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService service;

	@RequestMapping(value = ApiPath.TEST, method = RequestMethod.POST)
	public String test() {
		return "Test";
	}

	@PostMapping(ApiPath.LOGIN)
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

	@PostMapping(ApiPath.REGISTER)
	public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
		try {
			return ResponseEntity.ok(service.register(request));
		} catch (DuplicateKeyException dke) {
			return ResponseEntity.badRequest().body(dke.getMessage());
		}

	}

}
