package com.tekup.school.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person {
	
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idClasse")
	private Classe classe;
	
	
	
	

}
