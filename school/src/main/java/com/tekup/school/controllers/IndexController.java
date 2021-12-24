package com.tekup.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tekup.school.entities.Student;
import com.tekup.school.repository.StudentRepository;

@Controller
public class IndexController {

	
	@Autowired
	StudentRepository studentRepo;
	
@GetMapping("/login")
public String login() {
	return "login";
}

@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
private String index(Model model) {
	List<Student> females= studentRepo.findByGender("F");
	List<Student> males= studentRepo.findByGender("M");
	Long totalStudents= studentRepo.count()	;
	Long malePercentage=Math.round(males.size() *100 ) /totalStudents;
	Long femalePercentage=Math.round(females.size() *100 ) /totalStudents;
	model.addAttribute("female", femalePercentage);
	model.addAttribute("male", malePercentage);
	return "index";
}





@RequestMapping(value={"/students"}, method=RequestMethod.GET)
private String studentsList() {
	return "students";
}




}
