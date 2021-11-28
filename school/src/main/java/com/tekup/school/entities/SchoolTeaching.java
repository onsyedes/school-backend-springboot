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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SchoolTeaching {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="idPerson")
	private Teacher teacher;
	
	
	
	@ManyToOne
	@JoinColumn(name="idClasse")
	private Classe classe;
	
	
	@ManyToOne
	@JoinColumn(name="idSubject")
	private Subject subject;
	
	
	
	
	
	
	
}
