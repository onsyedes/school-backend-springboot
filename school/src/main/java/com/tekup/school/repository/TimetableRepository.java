package com.tekup.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

}
