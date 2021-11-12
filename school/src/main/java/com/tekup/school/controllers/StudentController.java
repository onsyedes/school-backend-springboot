package com.tekup.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tekup.school.Role;
import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Student;
import com.tekup.school.repository.ClasseRepository;
import com.tekup.school.repository.StudentRepository;

@RequestMapping("/")
@Controller

public class StudentController {

@Autowired
StudentRepository studentRepository;
@Autowired
ClasseRepository classeRepository;


@GetMapping("/list")
	public String findAllstudents(Model model) {
		
		List<Student> students= studentRepository.findAll();
		
		model.addAttribute("students", students);
		
		return "students" ;
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
	@RequestMapping(value = "/addstudent", method = { RequestMethod.GET, RequestMethod.POST })
	public String addstudent(@ModelAttribute("student") Student student) {
		
		student.setRole("Student");
		
		
		studentRepository.save(student);

		return "redirect:/";
	}
	
	@RequestMapping("/studentadd")
	public String studentadd(Model model) {
		Student student = new Student();
		List<Classe> classes =  classeRepository.findAll();
		model.addAttribute("classes", classes);
		model.addAttribute("student", student);

		return "addstudent";
	}
	
	
@GetMapping("/searchclasse/{classe}")
public String findStudentByClasse(@PathVariable(value="classe") Long classe){
	
	List<Student> listeStudent =studentRepository.findByClasse(classeRepository.findById(classe));
			return "redirect:/"; 
}

	
}
