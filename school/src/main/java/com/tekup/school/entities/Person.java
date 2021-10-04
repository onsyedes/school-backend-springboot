package com.tekup.school.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.tekup.school.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPerson;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column	
	private String adresse;
	@Column
	private String gender;
	@Column
	private String birthdate;
	@Column
	private Role role;
	@Column
	private String picture;
	
	
	
	

}
