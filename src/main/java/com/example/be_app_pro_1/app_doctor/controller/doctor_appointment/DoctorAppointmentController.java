package com.example.be_app_pro_1.app_doctor.controller.doctor_appointment;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant;
import com.example.be_app_pro_1.app_doctor.entity.Department.Department;
import com.example.be_app_pro_1.app_doctor.entity.DoctorAppointment.DoctorAppointment;
import com.example.be_app_pro_1.app_doctor.entity.User.User;
import com.example.be_app_pro_1.app_doctor.mapper.DoctorAppointmentMapper;
import com.example.be_app_pro_1.app_doctor.service.DepartmentService;
import com.example.be_app_pro_1.app_doctor.service.DoctorAppointmentService;
import com.example.be_app_pro_1.app_doctor.service.DoctorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = AppDoctorConstant.ApiPath.API + AppDoctorConstant.ApiPath.DOCTOR_APPOINTMENT)
@RequiredArgsConstructor
public class DoctorAppointmentController {

    private final DoctorService doctorService;

    private final DepartmentService departmentService;

    private final DoctorAppointmentService doctorAppointmentService;

    private final DoctorAppointmentMapper doctorAppointmentMapper;

    @GetMapping("/init")
    public void init() {
        doctorAppointmentService.initData3day();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> getDoctorAppointmentSignUp2(
            @RequestBody DoctorAppointmentSignUpRequest request) {
        if (request.getDepartmentCode() != null) {
            Department department = departmentService.getDepartment(request.getDepartmentCode());
            return ResponseEntity.ok().body(doctorAppointmentService.getListDoctorAppointmentDTO(request.getDate(), department));
        }
        return ResponseEntity.ok().body(doctorAppointmentService.getListDoctorAppointmentDTO(request.getDate()));
    }

    @GetMapping("/done")
    public ResponseEntity<Object> getListDoctorAppointmentDone() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User customUserDetails = (User) authentication.getPrincipal();
        List<DoctorAppointment> lstDoctorAppointment = doctorAppointmentService.getListDoctorAppointment(LocalDate.now(), customUserDetails);
        return ResponseEntity.ok().body(doctorAppointmentMapper.entityToListDoctorAppointmentDoneResponse(lstDoctorAppointment));
    }

    @GetMapping("/history")
    public ResponseEntity<Object> getAllDoctorPlan() {
        return ResponseEntity.ok().body(doctorService.getAllDoctorDTO());
    }

}
