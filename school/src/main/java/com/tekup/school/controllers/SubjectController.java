package com.tekup.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekup.school.entities.Subject;
import com.tekup.school.services.SubjectService;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	
	@Autowired
	SubjectService subServ;
	
	@GetMapping("/all")
	@ResponseBody
	public List<Subject> getAllSubjects(){
		return subServ.getAllSubjects();
	}

}
