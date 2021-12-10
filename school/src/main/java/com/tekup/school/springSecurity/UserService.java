package com.tekup.school.springSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tekup.school.dto.UserRegistrationDto;
import com.tekup.school.entities.Teacher;

public interface UserService extends UserDetailsService{
	Teacher save(UserRegistrationDto registrationDto);
}
