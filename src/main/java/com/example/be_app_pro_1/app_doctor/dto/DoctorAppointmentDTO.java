package com.example.be_app_pro_1.app_doctor.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointmentDTO {

	private String code;
	private String name;
	private String yearOfBirth;
	private String address;
	private String phone;
	private String email;
	private String gender;
	private DepartmentDTO department;
	private String degree;
	private Double numberOfStars;

	private List<AppointmentDTO> appointments;

}
