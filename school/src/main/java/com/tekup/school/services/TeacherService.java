package com.tekup.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.school.entities.Teacher;
import com.tekup.school.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepo;
	
public Teacher getTeacherById(Long id) {
	return teacherRepo.getById(id);
}




}
