package com.example.be_app_pro_1.app_doctor.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import org.springframework.stereotype.Service;

import com.example.be_app_pro_1.app_doctor.dto.DoctorAppointmentDTO;
import com.example.be_app_pro_1.app_doctor.entity.Department.Department;
import com.example.be_app_pro_1.app_doctor.entity.Doctor.Doctor;
import com.example.be_app_pro_1.app_doctor.entity.DoctorAppointment.DoctorAppointment;
import com.example.be_app_pro_1.app_doctor.entity.DoctorAppointment.DoctorAppointmentRepository;
import com.example.be_app_pro_1.app_doctor.entity.User.User;
import com.example.be_app_pro_1.app_doctor.enums.DoctorAppointmentStatus;
import com.example.be_app_pro_1.app_doctor.mapper.DoctorAppointmentMapper;
import com.example.be_app_pro_1.app_doctor.mapper.DoctorMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorAppointmentService {

    private final DoctorMapper doctorMapper;

    private final DoctorAppointmentMapper doctorAppointmentMapper;

    private final DoctorService doctorService;

    private final DoctorAppointmentRepository doctorAppointmentRepository;

    public void initData3day() {
		List<Doctor> lstDoctor = doctorService.getAllDoctor();
        LocalDate date = LocalDate.now();
        LocalTime businessStart = LocalTime.of(8, 0);
        LocalTime businessEnd = LocalTime.of(17, 0);
        LocalTime lunchStart = LocalTime.of(12, 0);
        LocalTime lunchEnd = LocalTime.of(13, 0);
        Duration slotDuration = Duration.ofHours(1);

        // Skip to the next working day if today is Friday, Saturday or Sunday
        if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
            date = date.plusDays(3);
        } else if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            date = date.plusDays(2);
        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.plusDays(1);
        }

        for (int i = 0; i < 3; ) { // Remove increment from here
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                for (Doctor doctor : lstDoctor) {
                    LocalTime slotStart = businessStart;
                    while (slotStart.isBefore(businessEnd)) {
                        LocalTime slotEnd = slotStart.plus(slotDuration);
                        if (slotEnd.isAfter(businessEnd)) {
                            break;
                        }

                        if (slotStart.equals(lunchStart)) {
                            slotStart = lunchEnd;
                            slotEnd = slotStart.plus(slotDuration);
                        }

                        // Check if the appointment already exists
                        if (doctorAppointmentRepository.findByDoctorAndDateAndStartTimeAndEndTime(doctor, date, slotStart, slotEnd).isEmpty()) {
                            DoctorAppointment doctorAppointment = new DoctorAppointment();
                            doctorAppointment.setDoctor(doctor);
                            doctorAppointment.setDate(date);
                            doctorAppointment.setStartTime(slotStart);
                            doctorAppointment.setEndTime(slotEnd);
                            doctorAppointment.setStatus(DoctorAppointmentStatus.AVAILABLE);
                            doctorAppointmentRepository.save(doctorAppointment);
                        }

                        slotStart = slotEnd;
                    }
                }
                i++; // Increment here only if the day is a working day
            }
            date = date.plusDays(1); // Always increment the date
        }
    }

    public List<DoctorAppointment> getListDoctorAppointment(LocalDate date) {
        return doctorAppointmentRepository.findByDate(date);
    }

    public List<DoctorAppointment> getListDoctorAppointment(LocalDate date, Department department) {
        return doctorAppointmentRepository.findByDateAndDoctorDepartment(date, department);
    }

    public List<DoctorAppointment> getListDoctorAppointment(LocalDate date, User user) {
        return doctorAppointmentRepository.findFutureAppointmentsForUser(date, user);
    }

    public List<DoctorAppointmentDTO> getListDoctorAppointmentDTO(LocalDate date) {
        List<DoctorAppointmentDTO> listDoctorAppointmentDTO = new ArrayList<>();

        List<Doctor> listDoctor = doctorService.getAllDoctor();
        return setDoctorAppointmentDTO(date, listDoctorAppointmentDTO, listDoctor);
    }

    public List<DoctorAppointmentDTO> getListDoctorAppointmentDTO(LocalDate date, Department department) {
        List<DoctorAppointmentDTO> listDoctorAppointmentDTO = new ArrayList<>();

        List<Doctor> listDoctor = doctorService.getAllDoctor(department);
        return setDoctorAppointmentDTO(date, listDoctorAppointmentDTO, listDoctor);
    }

    private List<DoctorAppointmentDTO> setDoctorAppointmentDTO(LocalDate date, List<DoctorAppointmentDTO> listDoctorAppointmentDTO,
            List<Doctor> listDoctor) {
        for (Doctor doctor : listDoctor) {
            DoctorAppointmentDTO doctorAppointmentDTO = doctorAppointmentMapper.doctorToDoctorAppointmentDTO(doctor);
            doctorAppointmentDTO.setAppointments(new ArrayList<>());

            List<DoctorAppointment> listDoctorAppointment = doctorAppointmentRepository.findByDateAndDoctor(date, doctor);

            for (DoctorAppointment doctorAppointment : listDoctorAppointment) {
                doctorAppointmentDTO.getAppointments().add(doctorAppointmentMapper.entityToAppointmentDTO(doctorAppointment));
            }
            listDoctorAppointmentDTO.add(doctorAppointmentDTO);
        }

        return listDoctorAppointmentDTO;
    }

}
