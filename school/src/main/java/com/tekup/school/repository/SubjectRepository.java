package com.tekup.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.school.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
