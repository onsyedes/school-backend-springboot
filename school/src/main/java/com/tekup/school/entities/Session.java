package com.tekup.school.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSession;
	private int startHour;
	private String day;
	private String duration;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Classe classe;
	
	

}
