package com.example.be_app_pro_1.app_doctor.controller.auth;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant.ApiPath;
import com.example.be_app_pro_1.app_doctor.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = ApiPath.API + ApiPath.AUTH)
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService service;

	@GetMapping(ApiPath.CHECK_AUTH)
	public ResponseEntity<Boolean> checkToken() {
		return ResponseEntity.ok(true);
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
