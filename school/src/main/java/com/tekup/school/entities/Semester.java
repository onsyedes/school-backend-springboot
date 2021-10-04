package com.tekup.school.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Semester {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idSemester;
	@Column
	private Short semesterNumber;
	
	

}
