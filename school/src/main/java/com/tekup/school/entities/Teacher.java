package com.tekup.school.entities;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Person {
	
	private String email;
	private String pwd;
	
	@ManyToMany(mappedBy = "teachers")
	private List<Classe> classes;
	
	@ManyToMany
	@JoinTable(
			name="teacher_subjects", joinColumns = @JoinColumn(name="idTeacher"),
			inverseJoinColumns = @JoinColumn(name="idSubject")
			)
	private List<Subject> subjects ;
	
	
	
	
	

}
