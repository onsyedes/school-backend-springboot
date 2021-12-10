package com.tekup.school.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	
@GetMapping("/login")
public String login() {
	return "login";
}

@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
private String index() {
	return "index";
}





@RequestMapping(value={"/students"}, method=RequestMethod.GET)
private String studentsList() {
	return "students";
}




}
