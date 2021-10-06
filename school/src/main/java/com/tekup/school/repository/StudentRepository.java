package com.tekup.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findByFirstNameContains(String firstname);
	List<Student> findByClasse(Optional<Classe> optional);
}
