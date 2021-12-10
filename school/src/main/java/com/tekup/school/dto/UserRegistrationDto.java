package com.tekup.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	public String role;
}
