package com.tekup.school.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Student;
import com.tekup.school.entities.Timetable;
import com.tekup.school.entities.TimetableFields;
import com.tekup.school.repository.StudentRepository;
import com.tekup.school.repository.TimetableFieldsRepository;
import com.tekup.school.repository.TimetableRepository;
import com.tekup.school.services.TimetableService;

@Controller
@RequestMapping("/absences")
public class AbsencesController {
	@Autowired
	TimetableFieldsRepository timetableFiledsRepo;
	@Autowired
	TimetableRepository timtableRepo;
	@Autowired
	TimetableService timetabService;
	@Autowired
	StudentRepository studentRepo;
	@Transient
	LinkedHashMap<String, String> path = new LinkedHashMap<>();
	@GetMapping("/")
	public String showEmploiClasse(Model model) {
		return findPaginatedTimeTables(1,"semester","asc", model);
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginatedTimeTables(@PathVariable(value="pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model ) 
	{
		int pageSize=10;
		Page<Timetable> page=timetabService.findPaginated(pageNo, pageSize,sortField,sortDir);
		List<Timetable> listeTimetables= page.getContent();
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc":"asc" );
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("timetables", listeTimetables);
		path.clear();
		path.put("Classes TimeTables","/absences/");
		model.addAttribute("path", path);
		return "absence_classes" ;
		
	}
	@GetMapping("/{id}")
	public String getTimetableFields(@PathVariable(value="id") Long id ,Model model) {
		Timetable timetab=timtableRepo.getById(id);
		Classe classe=timetab.getClasse();
		List<TimetableFields> timetabFields= timetableFiledsRepo.findByTimetable(timetab);
		 path.put(classe.getClassLabel(),"#");
		 path.put("Semester"+timetab.getSemester(),"#");
		 model.addAttribute("idTimetable", id);
		 model.addAttribute("path", path);
		 model.addAttribute("TimetableFields", timetabFields);
		 return "absence_session_select";
	}
	@GetMapping("/{id}/{idField}")
	public String getStudentsList(@PathVariable(value="id") Long id , @PathVariable(value="idField") Long idField ,Model model) {
		Timetable timetab= timtableRepo.getById(id);
		Classe classe=timetab.getClasse();
		List<Student> students = studentRepo.findByClasse(classe);
		model.addAttribute("classe", classe);
		model.addAttribute("students", students);
		model.addAttribute("idField", idField);
		model.addAttribute("timetable", id);
		 path.put(classe.getClassLabel(),"#");
		 path.put("Semester"+timetab.getSemester(),"#");
		 model.addAttribute("path", path);
		
		return "absence_registration";
	}
	
	
	@PostMapping("/absenceRegister")
	@ResponseBody
	public void registerAbsence() {
		
	}
}
