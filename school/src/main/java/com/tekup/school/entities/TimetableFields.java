package com.tekup.school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimetableFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String day;
	private int startHour;
	@ManyToOne
	@JoinColumn(name="idPerson")
	private Teacher teacher;
	@ManyToOne
	@JoinColumn(name="idSubject")
	private Subject subject;
	@ManyToOne
	@JoinColumn(name="idSalle")
	private Classroom salle;
	@ManyToOne
	@JoinColumn(name="idtimetable")
	private Timetable timetable;
	
	
}
