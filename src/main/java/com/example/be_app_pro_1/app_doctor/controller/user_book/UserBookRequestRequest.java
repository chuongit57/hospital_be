package com.example.be_app_pro_1.app_doctor.controller.user_book;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBookRequestRequest {

	private Integer doctorAppointmentId;

	private LocalDate date;
	private String phone;
	private int age;
	private double weight;
}
