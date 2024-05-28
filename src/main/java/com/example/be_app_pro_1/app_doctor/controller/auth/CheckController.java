package com.example.be_app_pro_1.app_doctor.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant.ApiPath;
import com.example.be_app_pro_1.app_doctor.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CheckController {

	private final AuthenticationService service;

	@GetMapping(ApiPath.TEST)
	public String test1() {
		return "Test1111111112";
	}

}
