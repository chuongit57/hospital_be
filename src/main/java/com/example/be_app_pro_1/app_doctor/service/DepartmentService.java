package com.example.be_app_pro_1.app_doctor.service;

import com.example.be_app_pro_1.app_doctor.dto.DepartmentDTO;
import com.example.be_app_pro_1.app_doctor.entity.Department.Department;
import com.example.be_app_pro_1.app_doctor.entity.Department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Department getDepartment(String code) {
        return departmentRepository.findByCode(code).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> getAllDepartmentDTO() {
        return departmentRepository.findDepartmentDTO();
    }



}
