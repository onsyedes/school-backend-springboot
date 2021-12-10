package com.tekup.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

}
