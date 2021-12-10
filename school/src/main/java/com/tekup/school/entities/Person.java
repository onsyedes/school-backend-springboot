package com.tekup.school.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthdate;
	@Column
	private String role;
	@Column
	private String picture;
	
	
	
	
	
	

}