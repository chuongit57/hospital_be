package com.example.be_app_pro_1.app_doctor.service;

import com.example.be_app_pro_1.app_doctor.entity.BloodGroup.BloodGroup;
import com.example.be_app_pro_1.app_doctor.entity.BloodGroup.BloodGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodGroupService {

    private final BloodGroupRepository bloodGroupRepository;

    public List<BloodGroup> getAll() {
        return bloodGroupRepository.findAll();
    }

}
