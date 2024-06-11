package com.example.be_app_pro_1.app_doctor.entity.Department;

import com.example.be_app_pro_1.app_doctor.dto.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByCode(String code);

    @Query("SELECT new com.example.be_app_pro_1.app_doctor.dto.DepartmentDTO(d.code, d.name) FROM Department d")
    List<DepartmentDTO> findDepartmentDTO();

}
