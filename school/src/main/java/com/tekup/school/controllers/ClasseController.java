package com.tekup.school.controllers;



import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tekup.school.entities.Classe;
import com.tekup.school.entities.SchoolTeaching;
import com.tekup.school.entities.Student;
import com.tekup.school.entities.Subject;
import com.tekup.school.entities.Teacher;
import com.tekup.school.repository.ClasseRepository;
import com.tekup.school.repository.SchoolTeachingRepository;
import com.tekup.school.repository.StudentRepository;
import com.tekup.school.repository.SubjectRepository;
import com.tekup.school.services.ClassService;
import com.tekup.school.services.StudentService;
import com.tekup.school.services.TeacherService;


@RequestMapping("/classes")
@Controller

public class ClasseController {
	@Autowired
	ClasseRepository classeRepo;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ClassService classServ;
	@Autowired
	StudentService studentServ;
	@Autowired
	TeacherService teacherServ;
	@Autowired
	SchoolTeachingRepository schoolTeachingRepo;
	@Autowired
	SubjectRepository subRepo;
	
	
	@javax.persistence.Transient
	LinkedHashMap<String, String> path = new LinkedHashMap<>();
	
	@GetMapping("/")
	public String listeClasses(Model model) {
		return findPaginatedClasses(1,"grade","asc", model);
	}
	
	
	
	@GetMapping("/{id}/studentsList")
	public String getStudentsByClass(Model model,@PathVariable(value="id") Long id) {
		return findPaginatedStudentsList(1, "firstName", "asc", id, model);
	}
	
	
	@GetMapping("{id}/studentsList/page/{pageNo}")
	public String findPaginatedStudentsList(@PathVariable(value="pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			@PathVariable(value="id") Long id,
			Model model ) 
	{
		int pageSize=10;
	    Page<Student> page =studentServ.findPaginatedListStudentsByClass(id, pageNo, pageSize, sortField, sortDir);
	    List<Student> students= page.getContent();
	    model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc":"asc" );
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("students", students);
		Optional<Classe> optEntity=classeRepo.findById(id);
		model.addAttribute("cls", optEntity.get());
		path.put("Classes","/classes/");
		path.put(optEntity.get().getClassLabel(), "#");
		path.put("Students List", "/classes/"+optEntity.get().getIdClasse()+"/studentsList") ;
		model.addAttribute("path", path);
 		return "class_students";
		
	}
	

	@GetMapping("/page/{pageNo}")
	public String findPaginatedClasses(@PathVariable(value="pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model ) 
	{
		int pageSize=10;
		Page<Classe> page=classServ.findPaginated(pageNo, pageSize,sortField,sortDir);
		List<Classe> listeClasses= page.getContent();
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc":"asc" );
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("classes", listeClasses);
		
		path.clear();
		path.put("Classes","/classes/");
		model.addAttribute("path", path);
		return "classes";
		
	}
	
	@PostMapping("/addnew")
	public String addNewClass(Classe classe) {
		classServ.addClass(classe);
		return "redirect:/classes/";
	}
	@GetMapping("/getclass/{id}")
	@ResponseBody
	public Optional<Classe> GetClassById(@PathVariable(value="id") Long id){
		return classeRepo.findById(id);
	}
	
	
	@RequestMapping(value="/update",method= {RequestMethod.PUT, RequestMethod.GET})
	public String updateClass(Classe classe) {
		classServ.updateClass(classe);
		return "redirect:/classes/";
	}
	
	@RequestMapping(value="/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteClasse(@PathVariable(value="id") Long id , RedirectAttributes redirectAttr) {
		List<Student> students= studentRepository.findByClasse(classeRepo.getById(id));
		if(students.size() == 0) {
				classServ.deleteClass(id);
				redirectAttr.addFlashAttribute("result", "ok");
		}else {
			redirectAttr.addFlashAttribute("result", "error");
		}
		return "redirect:/classes/";
	}
	
	@GetMapping("/teachaers-subjects/{id}")
	public String getTeachersAndSubjects(@PathVariable(value="id") Long id ,Model model) {
		
		List<SchoolTeaching> schoolTeach= schoolTeachingRepo.findByClasse(classeRepo.getById(id));
		model.addAttribute("schoolTeach", schoolTeach);
		model.addAttribute("classe", classeRepo.getById(id));
		path.put(classeRepo.getById(id).getClassLabel(), "#");
		path.put("Teachers & Subjects","/classes/teachaers-subjects/"+id);
		model.addAttribute("path", path);
		return "teachers_subjects";
	}
	
	@PostMapping("/teachaers-subjects/{id}/add")
	public String addTeachersAndSubjects(@PathVariable(value="id") Long idClasse , Long idPerson, Long idSubject ) {
		
		Teacher teacher= teacherServ.getTeacherById(idPerson);
	    Subject subject = subRepo.getById(idSubject);
	    Classe classe = classeRepo.getById(idClasse);
	    SchoolTeaching schoolTeach = new  SchoolTeaching();
	    schoolTeach.setClasse(classe);
	    schoolTeach.setTeacher(teacher);
	    schoolTeach.setSubject(subject);
	    schoolTeachingRepo.save(schoolTeach);
		return "redirect:/classes/teachaers-subjects/"+idClasse;
	}
	
	@RequestMapping(value="/teachaers-subjects/{idClasse}/delete/{id}",method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteSchoolTeachingField(@PathVariable(value="idClasse") Long idClasse,@PathVariable(value="id") Long id , RedirectAttributes redirectAttr) {
		
		classServ.deleteSchoolTeachingField(id);
		redirectAttr.addFlashAttribute("result", "ok");
		
		return "redirect:/classes/teachaers-subjects/"+idClasse ;
	}
	@GetMapping("/getclasses")
	@ResponseBody
	public List<Classe> getAllclasses(){
		return classeRepo.findAll();
	}
	
	
	
	
	

}
