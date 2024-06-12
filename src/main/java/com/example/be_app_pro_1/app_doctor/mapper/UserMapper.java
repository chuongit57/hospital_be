package com.example.be_app_pro_1.app_doctor.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.be_app_pro_1.app_doctor.dto.UserDTO;
import com.example.be_app_pro_1.app_doctor.entity.User.User;

@Component
@Mapper
public abstract class UserMapper {

	public abstract UserDTO entityToUserDTO(User user);

}
