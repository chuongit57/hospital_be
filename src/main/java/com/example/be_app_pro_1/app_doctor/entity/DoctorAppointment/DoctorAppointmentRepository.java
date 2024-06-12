package com.example.be_app_pro_1.app_doctor.entity.DoctorAppointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be_app_pro_1.app_doctor.entity.Department.Department;
import com.example.be_app_pro_1.app_doctor.entity.Doctor.Doctor;
import com.example.be_app_pro_1.app_doctor.entity.User.User;
import com.example.be_app_pro_1.app_doctor.enums.DoctorAppointmentStatus;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Integer> {

	List<DoctorAppointment> findByDate(LocalDate date);

	List<DoctorAppointment> findByDateAndDoctor(LocalDate date, Doctor doctor);

	List<DoctorAppointment> findByDateAndDoctorDepartment(LocalDate date, Department department);

	Optional<DoctorAppointment> findByDoctorAndDateAndStartTimeAndEndTime(Doctor doctor, LocalDate date, LocalTime startTime, LocalTime endTime);

	List<DoctorAppointment> findByDateAndUser(LocalDate date, User user);

	@Query("SELECT da FROM DoctorAppointment da WHERE da.date >= :currentDate AND da.user = :user")
	List<DoctorAppointment> findFutureAppointmentsForUser(@Param("currentDate") LocalDate currentDate, @Param("user") User user);

	Optional<DoctorAppointment> findByDateAndUserAndDoctorAndStatus(LocalDate date, User customUserDetails, Doctor doctor, DoctorAppointmentStatus doctorAppointmentStatus);

	@Query("SELECT da FROM DoctorAppointment da WHERE da.status = :status " +
			"AND da.doctor = :doctor " +
			"AND da.act is null " +
			"AND da.date >= :currentDate " +
			"AND (da.date > :currentDate OR da.startTime >= :currentTime)")
	List<DoctorAppointment> findUpcomingAppointments(
			@Param("status") DoctorAppointmentStatus status,
			@Param("doctor") Doctor doctor,
			@Param("currentDate") LocalDate currentDate,
			@Param("currentTime") LocalTime currentTime
	);

	@Query("SELECT da FROM DoctorAppointment da WHERE da.status = :status " +
			"AND da.doctor = :doctor " +
			"AND da.date >= :currentDate ")
	List<DoctorAppointment> findUpcomingAppointments(
			@Param("status") DoctorAppointmentStatus status,
			@Param("doctor") Doctor doctor,
			@Param("currentDate") LocalDate currentDate
	);
}
