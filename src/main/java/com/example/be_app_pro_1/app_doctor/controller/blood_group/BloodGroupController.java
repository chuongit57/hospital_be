package com.example.be_app_pro_1.app_doctor.controller.blood_group;

import java.util.List;

import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant;
import com.example.be_app_pro_1.app_doctor.entity.BloodGroup.BloodGroup;
import com.example.be_app_pro_1.app_doctor.service.BloodGroupService;
import com.example.be_app_pro_1.app_doctor.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppDoctorConstant.ApiPath.API + AppDoctorConstant.ApiPath.BLOOD_GROUP)
@RequiredArgsConstructor
public class BloodGroupController {

    private final BloodGroupService bloodGroupService;

    @GetMapping("/all")
    public ResponseEntity<List<BloodGroup>> test() {
        return ResponseEntity.ok().body(bloodGroupService.getAll());
    }

}
