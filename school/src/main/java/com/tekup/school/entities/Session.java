package com.tekup.school.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSession;
	@Column
	private String startHour;
	@Column
	private String date;
	@Column
	private String duration;
	

}
