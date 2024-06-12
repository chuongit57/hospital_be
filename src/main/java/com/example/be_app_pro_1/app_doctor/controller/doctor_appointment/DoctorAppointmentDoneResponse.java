package com.example.be_app_pro_1.app_doctor.controller.doctor_appointment;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.be_app_pro_1.app_doctor.dto.DoctorDTO;
import com.example.be_app_pro_1.app_doctor.entity.User.User;
import com.example.be_app_pro_1.app_doctor.enums.DoctorAppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointmentDoneResponse {

	private Integer id;

	private DoctorDTO doctor;

	private LocalTime startTime;

	private LocalTime endTime;

	private LocalDate date;

	private DoctorAppointmentStatus status;

	private User user;

	private String name;

	private String gender;

	private String description;

	private Integer age;

	private Double weight;

	private Double height;

	private String phone;
}
