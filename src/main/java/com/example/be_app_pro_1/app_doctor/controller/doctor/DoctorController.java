package com.example.be_app_pro_1.app_doctor.controller.doctor;

import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant;
import com.example.be_app_pro_1.app_doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppDoctorConstant.ApiPath.API + AppDoctorConstant.ApiPath.DOCTOR)
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<Object> getAllDoctor() {
        return ResponseEntity.ok().body(doctorService.getAllDoctorDTO());
    }

    @PostMapping("/doctors-plan-now")
    public ResponseEntity<Object> getAllDoctorPlan() {
        return ResponseEntity.ok().body(doctorService.getAllDoctorDTO());
    }

    @GetMapping("/top-5-doctor")
    public ResponseEntity<Object> getTop5Doctor() {
        return ResponseEntity.ok().body(doctorService.getTop5DoctorDTO());
    }

}
