package com.example.be_app_pro_1.app_doctor.controller.doctor_appointment;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointmentSignUpRequest {

	private String departmentCode;

	private LocalDate date;
}
