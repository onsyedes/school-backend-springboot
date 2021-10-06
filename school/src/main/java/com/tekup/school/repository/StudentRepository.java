package com.tekup.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findByFirstNameContains(String firstname);

}
