package com.example.be_app_pro_1.app_doctor.controller.doctor;

import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant;
import com.example.be_app_pro_1.app_doctor.service.DepartmentService;
import com.example.be_app_pro_1.app_doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppDoctorConstant.ApiPath.API + AppDoctorConstant.ApiPath.DOCTOR)
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllDoctorDTO() {
        return ResponseEntity.ok().body(doctorService.getAllDoctorDTO());
    }

}
