package com.tekup.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekup.school.entities.Classroom;
import com.tekup.school.repository.ClassroomRepository;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {
	@Autowired
	ClassroomRepository classroomRepo;

	@GetMapping("/all")
	@ResponseBody
	public List<Classroom> getAllClassrooms(){
		return classroomRepo.findAll();
	}
}
