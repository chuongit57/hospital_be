package com.example.be_app_pro_1.app_doctor.service;

import com.example.be_app_pro_1.app_doctor.dto.DoctorDTO;
import com.example.be_app_pro_1.app_doctor.entity.Department.Department;
import com.example.be_app_pro_1.app_doctor.entity.Doctor.Doctor;
import com.example.be_app_pro_1.app_doctor.entity.Doctor.DoctorRepository;
import com.example.be_app_pro_1.app_doctor.mapper.DoctorMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorMapper doctorMapper;

    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getAllDoctor(Department department) {
        return doctorRepository.findByDepartmentOrderByNumberOfStarsDesc(department);
    }

    public List<DoctorDTO> getAllDoctorDTO() {
        return doctorMapper.entityToListDoctorDTO(doctorRepository.findAll());
    }

    public List<DoctorDTO> getAllDoctorDTO(Department department) {
        return doctorMapper.entityToListDoctorDTO(doctorRepository.findByDepartmentOrderByNumberOfStarsDesc(department));
    }

    public List<DoctorDTO> getTop5DoctorDTO() {
        List<Doctor> lstTop5Doctor = doctorRepository.findByOrderByNumberOfStarsDesc(Limit.of(5));
        return doctorMapper.entityToListDoctorDTO(lstTop5Doctor);
    }

}
