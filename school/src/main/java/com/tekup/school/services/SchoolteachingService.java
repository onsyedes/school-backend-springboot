package com.tekup.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.school.entities.SchoolTeaching;
import com.tekup.school.entities.Teacher;
import com.tekup.school.repository.SchoolTeachingRepository;
import com.tekup.school.repository.TeacherRepository;

@Service
public class SchoolteachingService {

	@Autowired
	SchoolTeachingRepository schoolteachRepo;
	@Autowired
	TeacherRepository teacherRepo;
	
	
	
	public void deleteFieldsByTeacher(Long Teacherid) {
		Teacher teacher=teacherRepo.getById(Teacherid);
		List<SchoolTeaching> list=schoolteachRepo.findByTeacher(teacher);
		for(int i=0;i<list.size();i++) {
			schoolteachRepo.delete(list.get(i));
		}
		
	}
	
	
}
