package com.tekup.school.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.school.entities.Teacher;
import com.tekup.school.entities.Timetable;
import com.tekup.school.entities.TimetableFields;

@Repository
public interface TimetableFieldsRepository extends JpaRepository<TimetableFields, Long>{
   public List<TimetableFields> findByTimetable(Timetable timetable);
   public List<TimetableFields> findByTeacher(Teacher teacher);
   
}
