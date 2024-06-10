package com.example.be_app_pro_1.app_doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String code;
    private String name;
    private String yearOfBirth;
    private String address;
    private String phone;
    private String email;
    private String gender;
    private DepartmentDTO department;
    private String degree;
    private Double numberOfStars;
}
