package com.example.be_app_pro_1.app_doctor.entity.Doctor;

import com.example.be_app_pro_1.app_doctor.entity.Department.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private String code;

    private String name;

    private String yearOfBirth;

    private String address;

    private String phone;

    private String email;

    private String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    private String degree;

    private Double numberOfStars;

}

