package com.tekup.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Classe;
@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long>{
	
	

}
