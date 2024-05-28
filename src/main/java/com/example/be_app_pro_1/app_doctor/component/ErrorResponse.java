package com.example.be_app_pro_1.app_doctor.component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private String message;
	private String detail;

	public ErrorResponse(String message, String detail) {
		this.message = message;
		this.detail = detail;
	}

}
