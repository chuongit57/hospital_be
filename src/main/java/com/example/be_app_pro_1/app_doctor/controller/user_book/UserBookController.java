package com.example.be_app_pro_1.app_doctor.controller.user_book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_app_pro_1.app_doctor.constant.AppDoctorConstant;
import com.example.be_app_pro_1.app_doctor.controller.doctor_appointment.DoctorAppointmentDoneResponse;
import com.example.be_app_pro_1.app_doctor.controller.doctor_appointment.DoctorAppointmentSignUpRequest;
import com.example.be_app_pro_1.app_doctor.entity.Department.Department;
import com.example.be_app_pro_1.app_doctor.entity.DoctorAppointment.DoctorAppointment;
import com.example.be_app_pro_1.app_doctor.entity.DoctorAppointment.DoctorAppointmentRepository;
import com.example.be_app_pro_1.app_doctor.entity.User.User;
import com.example.be_app_pro_1.app_doctor.enums.DoctorAppointmentAct;
import com.example.be_app_pro_1.app_doctor.enums.DoctorAppointmentStatus;
import com.example.be_app_pro_1.app_doctor.mapper.DoctorAppointmentMapper;
import com.example.be_app_pro_1.app_doctor.service.DepartmentService;
import com.example.be_app_pro_1.app_doctor.service.DoctorAppointmentService;
import com.example.be_app_pro_1.app_doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = AppDoctorConstant.ApiPath.API + AppDoctorConstant.ApiPath.USER_BOOK)
@RequiredArgsConstructor
public class UserBookController {

    private final DoctorService doctorService;

    private final DepartmentService departmentService;

    private final DoctorAppointmentService doctorAppointmentService;
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorAppointmentMapper doctorAppointmentMapper;

    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody UserBookRequestRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User customUserDetails = (User) authentication.getPrincipal();

            Optional<DoctorAppointment> optionalDoctorAppointment = doctorAppointmentRepository.findById(request.getDoctorAppointmentId());

            if (optionalDoctorAppointment.isPresent()) {
                DoctorAppointment doctorAppointment = optionalDoctorAppointment.get();

                if (doctorAppointment.getStatus() == DoctorAppointmentStatus.BOOKED) {
                    return ResponseEntity.badRequest().body("DoctorAppointment is already booked");
                }

                doctorAppointmentRepository.findByDateAndUserAndDoctorAndStatus(LocalDate.now(), customUserDetails, doctorAppointment.getDoctor(), DoctorAppointmentStatus.BOOKED).ifPresent(d -> {
                    throw new RuntimeException("Bạn chỉ được book 1 khung giờ cho 1 bác sĩ");
                });

                doctorAppointment.setStatus(DoctorAppointmentStatus.BOOKED);
                doctorAppointment.setUser(customUserDetails);
                doctorAppointment.setName(request.getName());
                doctorAppointment.setGender(request.getGender());
                doctorAppointment.setAge(request.getAge());
                doctorAppointment.setPhone(request.getPhone());
                doctorAppointment.setWeight(request.getWeight());
                doctorAppointment.setHeight(request.getHeight());
                doctorAppointment.setDescription(request.getDescription());
                doctorAppointmentRepository.save(doctorAppointment);

                return ResponseEntity.ok("DoctorAppointment is booked successfully");
            } else {
                return ResponseEntity.badRequest().body("DoctorAppointment not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/remove-book")
    public ResponseEntity<?> delete(@RequestBody UserBookRequestRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User customUserDetails = (User) authentication.getPrincipal();

            Optional<DoctorAppointment> optionalDoctorAppointment = doctorAppointmentRepository.findById(request.getDoctorAppointmentId());

            if (optionalDoctorAppointment.isPresent()) {
                DoctorAppointment doctorAppointment = optionalDoctorAppointment.get();

                if (doctorAppointment.getStatus() == DoctorAppointmentStatus.BOOKED && customUserDetails.getId().equals(doctorAppointment.getUser().getId())) {
                    doctorAppointment.setStatus(DoctorAppointmentStatus.AVAILABLE);
                    doctorAppointment.setUser(null);
                    doctorAppointment.setAge(null);
                    doctorAppointment.setGender(null);
                    doctorAppointment.setName(null);
                    doctorAppointment.setDescription(null);
                    doctorAppointment.setPhone(null);
                    doctorAppointment.setWeight(null);
                    doctorAppointmentRepository.save(doctorAppointment);

                    return ResponseEntity.ok("DoctorAppointment is removed successfully");
                } else {
                    return ResponseEntity.badRequest().body("DoctorAppointment is not booked by you");
                }
            } else {
                return ResponseEntity.badRequest().body("DoctorAppointment not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }

    @PostMapping("/list-book")
    public ResponseEntity<List<DoctorAppointmentDoneResponse>> getUpcomingAppointments(
            @RequestBody DoctorAppointmentSignUpRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User customUserDetails = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(doctorAppointmentMapper.entityToListDoctorAppointmentDoneResponse(doctorAppointmentService.getUpcomingAppointments(customUserDetails.getDoctor(),  request.getDate())));
    }

    @PostMapping("/doctor-completed-book")
    public ResponseEntity<?> doctorCompleted(@RequestBody UserBookRequestRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User customUserDetails = (User) authentication.getPrincipal();

            Optional<DoctorAppointment> optionalDoctorAppointment = doctorAppointmentRepository.findById(request.getDoctorAppointmentId());

            if (optionalDoctorAppointment.isPresent()) {
                DoctorAppointment doctorAppointment = optionalDoctorAppointment.get();

                if (doctorAppointment.getStatus() == DoctorAppointmentStatus.BOOKED && customUserDetails.getDoctor().getId().equals(doctorAppointment.getDoctor().getId())) {

                    if (doctorAppointment.getAct() != null) {
                        return ResponseEntity.badRequest().body("Cuộc hẹn này đã được hoàn thành");
                    }
                    doctorAppointment.setAct(DoctorAppointmentAct.COMPLETED);
                    doctorAppointment.setDetail(request.getDetail());
                    doctorAppointmentRepository.save(doctorAppointment);

                    return ResponseEntity.ok("DoctorAppointment is removed successfully");
                } else {
                    return ResponseEntity.badRequest().body("DoctorAppointment is not booked by you");
                }
            } else {
                return ResponseEntity.badRequest().body("DoctorAppointment not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }

    @PostMapping("/doctor-cancel-book")
    public ResponseEntity<?> doctorCancel(@RequestBody UserBookRequestRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User customUserDetails = (User) authentication.getPrincipal();

            Optional<DoctorAppointment> optionalDoctorAppointment = doctorAppointmentRepository.findById(request.getDoctorAppointmentId());

            if (optionalDoctorAppointment.isPresent()) {
                DoctorAppointment doctorAppointment = optionalDoctorAppointment.get();

                if (doctorAppointment.getStatus() == DoctorAppointmentStatus.BOOKED && customUserDetails.getDoctor().getId().equals(doctorAppointment.getDoctor().getId())) {

                    if (doctorAppointment.getAct() != null) {
                        return ResponseEntity.badRequest().body("Cuộc hẹn này đã được hoàn thành");
                    }
                    doctorAppointment.setAct(DoctorAppointmentAct.CANCELLED);
                    doctorAppointment.setDetail(request.getDetail());
                    doctorAppointmentRepository.save(doctorAppointment);

                    return ResponseEntity.ok("DoctorAppointment is removed successfully");
                } else {
                    return ResponseEntity.badRequest().body("DoctorAppointment is not booked by you");
                }
            } else {
                return ResponseEntity.badRequest().body("DoctorAppointment not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }

}
