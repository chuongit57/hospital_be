package com.example.be_app_pro_1.app_doctor.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.be_app_pro_1.app_doctor.dto.DoctorDTO;
import com.example.be_app_pro_1.app_doctor.entity.Doctor.Doctor;

@Component
@Mapper
public abstract class DoctorMapper {

	public abstract DoctorDTO entityToDoctorDTO(Doctor doctor);

	public abstract List<DoctorDTO> entityToListDoctorDTO(List<Doctor> doctors);

}
