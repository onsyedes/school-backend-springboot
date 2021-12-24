package com.tekup.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.SchoolTeaching;
import com.tekup.school.entities.Teacher;

@Repository
public interface SchoolTeachingRepository extends JpaRepository<SchoolTeaching, Long>{
	  public List<SchoolTeaching> findByClasse(Classe classe);
	  public List<SchoolTeaching> findByTeacher(Teacher teacher);
	  
}
