package com.example.be_app_pro_1.app_doctor.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.be_app_pro_1.app_doctor.controller.doctor_appointment.DoctorAppointmentDoneResponse;
import com.example.be_app_pro_1.app_doctor.dto.AppointmentDTO;
import com.example.be_app_pro_1.app_doctor.dto.DoctorAppointmentDTO;
import com.example.be_app_pro_1.app_doctor.entity.Doctor.Doctor;
import com.example.be_app_pro_1.app_doctor.entity.DoctorAppointment.DoctorAppointment;

@Component
@Mapper
public abstract class DoctorAppointmentMapper {

	public abstract AppointmentDTO entityToAppointmentDTO(DoctorAppointment doctorAppointment);

	public abstract DoctorAppointmentDTO doctorToDoctorAppointmentDTO(Doctor doctor);

	public abstract List<AppointmentDTO> entityToListAppointmentDTO(List<DoctorAppointment> doctorAppointments);

	public abstract List<DoctorAppointmentDoneResponse> entityToListDoctorAppointmentDoneResponse(List<DoctorAppointment> doctorAppointments);

}
