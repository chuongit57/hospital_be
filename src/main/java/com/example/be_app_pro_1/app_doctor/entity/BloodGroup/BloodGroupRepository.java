package com.example.be_app_pro_1.app_doctor.entity.BloodGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroup, Integer> {

}
