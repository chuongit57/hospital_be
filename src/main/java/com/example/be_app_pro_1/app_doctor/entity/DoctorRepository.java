package com.example.be_app_pro_1.app_doctor.entity;

import com.example.be_app_pro_1.app_doctor.dto.DoctorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("SELECT new com.example.be_app_pro_1.app_doctor.dto.DoctorDTO(d.code, d.name, d.yearOfBirth, d.address, d.phone, d.email, d.gender, d.degree, " +
            "new com.example.be_app_pro_1.app_doctor.dto.DepartmentDTO(dep.code, dep.name)) " +
            "FROM Doctor d JOIN d.department dep")
    List<DoctorDTO> findAllDoctorsWithDepartment();

}
