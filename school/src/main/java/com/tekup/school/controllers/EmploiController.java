package com.tekup.school.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Classroom;
import com.tekup.school.entities.Subject;
import com.tekup.school.entities.Teacher;
import com.tekup.school.entities.Timetable;
import com.tekup.school.entities.TimetableFields;
import com.tekup.school.repository.ClassroomRepository;
import com.tekup.school.repository.SubjectRepository;
import com.tekup.school.repository.TeacherRepository;
import com.tekup.school.repository.TimetableFieldsRepository;
import com.tekup.school.repository.TimetableRepository;
import com.tekup.school.services.ClassService;
import com.tekup.school.services.TimetableFieldsService;
import com.tekup.school.services.TimetableService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/emplois")
public class EmploiController {

	
	@javax.persistence.Transient
	LinkedHashMap<String, String> path = new LinkedHashMap<>();
	@Autowired
	TimetableRepository timtableRepo;
	@Autowired
	TimetableFieldsRepository timetableFiledsRepo;
	@Autowired
	TimetableFieldsService timetabFieldsService;
	@Autowired
	SubjectRepository subjectRepo;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	ClassroomRepository classroomRepo;
	@Autowired
	ClassService classServ;
	@Autowired
	TimetableFieldsRepository timetabFieldsRepo;
	@Autowired
	TimetableService timetabService;
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
		path.put("TimeTables","/emplois/");
		model.addAttribute("path", path);
		return "emploi_classes" ;
		
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
		 return "emploi";
	}
	@PostMapping("/setfield/{id}")
	public String setTimetableFields(@PathVariable(value="id") Long idTimetab,Long idPerson , Long idSubject ,Long idSalle
		,String day, int startHour	, RedirectAttributes redirectAttr) {
		
		Classroom salle=classroomRepo.getById(idSalle);
		Teacher teacher=teacherRepo.getById(idPerson);
		Subject subject =subjectRepo.getById(idSubject);
		Timetable timetab=timetabService.getById(idTimetab);
		List<TimetableFields> timetabFields= timetabFieldsRepo.findAll();
		if(timetabFieldsService.save(salle, teacher, timetab, subject, day, startHour, timetabFields)) {
			redirectAttr.addFlashAttribute("result", "ok");
		}else {	redirectAttr.addFlashAttribute("result", "error");}
		return "redirect:/emplois/"+idTimetab;
	}
	
	
	@RequestMapping(value="/delete/{timetab}/{id}",method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteTimetableField(@PathVariable(value="id") Long idTimetabField ,@PathVariable(value="timetab") Long timetab  ) {
		timetabFieldsService.deleteField(idTimetabField);
		return "redirect:/emplois/"+timetab;
	}
	
	
}
