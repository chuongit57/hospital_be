package com.example.be_app_pro_1.app_doctor.entity;

import com.example.be_app_pro_1.app_doctor.dto.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("SELECT new com.example.be_app_pro_1.app_doctor.dto.DepartmentDTO(d.code, d.name) FROM Department d")
    List<DepartmentDTO> findDepartmentDTO();

}
