package com.tekup.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Student;
import com.tekup.school.repository.ClasseRepository;
import com.tekup.school.repository.StudentRepository;

@RestController
//@RequestMapping("/students")
public class StudentController {

@Autowired
StudentRepository studentRepository;
@Autowired
ClasseRepository classeRepository;


/*
@GetMapping("/students_list")
public ModelAndView showStudents(Model model) {
	
	 
}
*/



@PostMapping("/add")
public Student createStudent(@RequestBody Student student) {
	return studentRepository.save(student);
}


@PostMapping("/update")
public Student updateStudent(@RequestBody Student student) {
	return studentRepository.save(student);
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<Student> deleteStudent(@PathVariable(value="id") Long id) throws ResourceNotFoundException{
	Student student= studentRepository.findById(id).orElseThrow(
			()-> new ResourceNotFoundException("Student not found for this id "+id));
	studentRepository.delete(student);
	return new ResponseEntity<>(student, HttpStatus.OK);
	
}
@GetMapping("/search/{firstname}")
public List<Student> findStudentByFirstname(@PathVariable(value="firstname") String firstname){
	return studentRepository.findByFirstNameContains(firstname);
}
@GetMapping("/searchclasse/{classe}")
public List<Student> findStudentByClasse(@PathVariable(value="classe") Long classe){
	return studentRepository.findByClasse(classeRepository.findById(classe));
}

	
}
