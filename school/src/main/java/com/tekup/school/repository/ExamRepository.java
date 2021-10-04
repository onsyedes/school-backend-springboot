package com.tekup.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Exam;
@Repository

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
