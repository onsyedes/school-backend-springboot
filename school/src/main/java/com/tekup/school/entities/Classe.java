package com.tekup.school.entities;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClasse;
	private String classLabel;
	private int grade;
	
	@ManyToMany
	@JoinTable(
			name="teacher_classe", joinColumns = @JoinColumn(name="idClasse"),
			inverseJoinColumns = @JoinColumn(name="idTeacher")
			)
	private List<Teacher> teachers ;
	
	
	
	
	@OneToMany(mappedBy = "classe")
	private List<Student> students ;
	
	


}
