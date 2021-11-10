package com.tekup.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.school.entities.Student;
import com.tekup.school.repository.ClasseRepository;
import com.tekup.school.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

@Autowired
StudentRepository studentRepository;
@Autowired
ClasseRepository classeRepository;


@GetMapping("/")
	public String findAllstudents(Model model) {
		
		List<Student> students= studentRepository.findAll();
		
		model.addAttribute("students", students);
		
		return "index" ;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable(value="id") Long id) throws ResourceNotFoundException{
		Student student = studentRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("student not found for this id "+id));
		studentRepository.delete(student);
		return "redirect:/";
		
	}
	
	
	@PostMapping("/student/{id}")
	public String uptadestudent(Model model , @PathVariable("id") Long id) {
		
		Student student = studentRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("student not found for this id "+id));
		
		model.addAttribute("student",student);
		studentRepository.save(student);
		return"redirect:/";
		
	}
	
	@PostMapping("/addstudent")
	public String addstudent(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		studentRepository.save(student);
		return "redirect:/";
	}
	
	
@GetMapping("/searchclasse/{classe}")
public String findStudentByClasse(@PathVariable(value="classe") Long classe){
	
	List<Student> listeStudent =studentRepository.findByClasse(classeRepository.findById(classe));
			return "redirect:/"; 
}

	
}
