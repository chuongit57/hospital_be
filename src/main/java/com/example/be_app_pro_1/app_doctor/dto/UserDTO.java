package com.example.be_app_pro_1.app_doctor.dto;

import com.example.be_app_pro_1.app_doctor.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Role role;
	private DoctorDTO doctor;
}
