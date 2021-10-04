package com.tekup.school.entities;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSubject;
	
	private String subjectLabel;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idModule")
    private Module module;
	
	@ManyToMany(mappedBy = "subjects")
	private List<Teacher> teachers;
   


}
