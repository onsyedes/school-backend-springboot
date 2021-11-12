package com.tekup.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tekup.school.entities.Classe;
import com.tekup.school.repository.ClasseRepository;

@Controller
public class ClasseController {
	@Autowired
	ClasseRepository classeRepo ;
	
	@GetMapping
	public String listeClasses(Model model) {
		
		List<Classe> classes = classeRepo.findAll();
		model.addAttribute("Classes", classes);
		
		return "";
		
	}
	

}
