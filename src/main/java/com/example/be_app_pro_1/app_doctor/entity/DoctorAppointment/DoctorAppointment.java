package com.example.be_app_pro_1.app_doctor.entity.DoctorAppointment;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.be_app_pro_1.app_doctor.entity.Doctor.Doctor;
import com.example.be_app_pro_1.app_doctor.entity.User.User;
import com.example.be_app_pro_1.app_doctor.enums.DoctorAppointmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor_appointment")
public class DoctorAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	private LocalTime startTime;

	private LocalTime endTime;

	private LocalDate date;

	@Enumerated(EnumType.STRING)
	private DoctorAppointmentStatus status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String description;

	private Integer age;

	private Double weight;

	private Double height;

	private String phone;

}
