package com.example.be_app_pro_1.app_doctor.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.be_app_pro_1.app_doctor.dto.DepartmentDTO;
import com.example.be_app_pro_1.app_doctor.entity.Department.Department;

@Component
@Mapper
public abstract class DepartmentMapper {

	public abstract DepartmentDTO entityToDepartmentDTO(Department department);

}
