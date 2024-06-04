package com.example.be_app_pro_1.app_doctor.service;

import com.example.be_app_pro_1.app_doctor.dto.DoctorDTO;
import com.example.be_app_pro_1.app_doctor.entity.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final DoctorRepository patientRepository;

    public List<DoctorDTO> getAllDoctorDTO() {
        return patientRepository.findAllDoctorsWithDepartment();
    }

}
