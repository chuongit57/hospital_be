package com.example.be_app_pro_1.app_doctor.entity.Doctor;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.be_app_pro_1.app_doctor.entity.Department.Department;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	List<Doctor> findByOrderByNumberOfStarsDesc(Limit limit);

	List<Doctor> findByDepartmentOrderByNumberOfStarsDesc(Department department);

}
