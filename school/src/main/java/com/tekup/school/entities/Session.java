package com.tekup.school.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSession;
	
	@OneToOne
	@JoinColumn(name="idfield")
	private TimetableFields timetableField;
	@CreationTimestamp
	private Date day;
	
	
	
	

}
