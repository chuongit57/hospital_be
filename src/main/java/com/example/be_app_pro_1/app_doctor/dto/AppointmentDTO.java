package com.example.be_app_pro_1.app_doctor.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.be_app_pro_1.app_doctor.enums.DoctorAppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

	private LocalTime startTime;

	private LocalTime endTime;

	private LocalDate date;

	private DoctorAppointmentStatus status;

	private Integer id;

}
