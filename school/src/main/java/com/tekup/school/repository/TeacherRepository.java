package com.tekup.school.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Student;
import com.tekup.school.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	


}