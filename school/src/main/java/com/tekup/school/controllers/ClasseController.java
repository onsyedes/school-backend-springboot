package com.tekup.school.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Student;
import com.tekup.school.repository.ClasseRepository;
import com.tekup.school.repository.StudentRepository;

@RequestMapping("/classes")
@Controller

public class ClasseController {
	@Autowired
	ClasseRepository classeRepo;
	@Autowired
	StudentRepository studentRepository;
	@GetMapping("/list")
	public String listeClasses(Model model) {
		
		List<Classe> classes = classeRepo.findAll();
		model.addAttribute("classes", classes);
		return "classes";
	}
	
	@GetMapping("/{id}")
	public String getStudentsByClass(Model model,@PathVariable(value="id") Long id) {
		
		List<Student> students =studentRepository.findByClasse(classeRepo.findById(id));
		Optional<Classe> optEntity=classeRepo.findById(id);
		model.addAttribute("cls", optEntity.get());
		model.addAttribute("students", students);
		return "class_students";
	}
	
	

}
