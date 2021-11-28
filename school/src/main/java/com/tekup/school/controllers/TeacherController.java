package com.tekup.school.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekup.school.entities.Teacher;
import com.tekup.school.repository.TeacherRepository;

@Controller
@RequestMapping("/teachers")

public class TeacherController {
	@Autowired
	TeacherRepository teacherRepo;
	
	@GetMapping("/")
	@ResponseBody
	public List<Teacher> findAllTeachers(Model model) {
		
		return teacherRepo.findAll() ;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable(value="id") Long id) throws ResourceNotFoundException{
		Teacher teacher = teacherRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Teacher not found for this id "+id));
		teacherRepo.delete(teacher);
		return "redirect:/";
		
	}
	
	
	@PostMapping("/teacher/{id}")
	public String uptadeTeacher(Model model , @PathVariable("id") Long id) {
		
		Teacher teacher = teacherRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Teacher not found for this id "+id));
		
		model.addAttribute("teacher",teacher);
		teacherRepo.save(teacher);
		return"redirect:/";
		
	}
	
	@PostMapping("/addteacher")
	public String addTeacher(Model model) {
		
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		teacherRepo.save(teacher);
		return "redirect:/";
	}

}
