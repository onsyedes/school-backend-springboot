package com.tekup.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>{

}
