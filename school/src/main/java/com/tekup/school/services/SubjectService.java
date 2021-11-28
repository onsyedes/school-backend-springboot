package com.tekup.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.tekup.school.entities.Subject;
import com.tekup.school.repository.SubjectRepository;

@Service
public class SubjectService {

	
	@Autowired
	SubjectRepository subRepo;
	
	
	
	public List<Subject> getAllSubjects(){
		return subRepo.findAll();
	}
	
	
	
	
}
