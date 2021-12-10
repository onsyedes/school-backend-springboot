package com.tekup.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tekup.school.entities.Teacher;
import com.tekup.school.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
public Teacher getTeacherById(Long id) {
	return teacherRepo.getById(id);
}


public void Save(Teacher teacher) {
	teacher.setPwd(passwordEncoder.encode(teacher.getPwd()));
	teacher.setRole("TEACHER");
	teacherRepo.save(teacher);
}



}
