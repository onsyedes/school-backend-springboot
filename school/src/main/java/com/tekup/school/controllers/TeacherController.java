package com.tekup.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Teacher;
import com.tekup.school.repository.ClasseRepository;
import com.tekup.school.repository.TeacherRepository;

@Controller
@RequestMapping("/")

public class TeacherController {

@Autowired
TeacherRepository teacherRepository;
@Autowired
ClasseRepository classeRepository;


@GetMapping("/teacherslist")
	public String findAllteachers(Model model) {
		
		List<Teacher> teachers= teacherRepository.findAll();
		
		model.addAttribute("teachers", teachers);
		
		return "teachers" ;
	}
	
	@GetMapping("deleteteacher/{id}")
	public String deleteteacher(@PathVariable(value="id") Long id) throws ResourceNotFoundException{
		Teacher teacher = teacherRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("teacher not found for this id "+id));
		teacherRepository.delete(teacher);
		return "redirect:/";
		
	}
	
	
	
	
	@PostMapping("updateteacher/{id}")
    public String updateteacher(@PathVariable("id") long id, Teacher teacher,
        Model model) {
       
		teacher.setRole("teacher");
		teacher.setIdPerson(id);
        teacherRepository.save(teacher);
        model.addAttribute("teachers", teacherRepository.findAll());
        return "redirect:/";
    }
	
	@GetMapping("editteacher/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Teacher teacher = teacherRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("teacher not found for this id "+id));
        model.addAttribute("teacher", teacher);
        return "editteacher";
    }
	
	
	
	
	
	
	@RequestMapping(value = "/addteacher", method = { RequestMethod.GET, RequestMethod.POST })
	public String addteacher(@ModelAttribute("teacher") Teacher teacher,@ModelAttribute("classes") Classe classe) {
		
		teacher.setRole("Teacher");
		
		
		teacherRepository.save(teacher);

		return "redirect:/";
	}
	
	@RequestMapping("/teacheradd")
	public String teacheradd(Model model) {
		Teacher teacher = new Teacher();
		List<Classe> classes =  classeRepository.findAll();
		model.addAttribute("classes", classes);
		model.addAttribute("teacher", teacher);

		return "addteacher";
	}
	
	


	
}