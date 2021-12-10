package com.tekup.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class TimetableFieldsService {
	@Autowired
	TimetableRepository timtableRepo;
	@Autowired
	TimetableFieldsRepository timetableFiledsRepo;
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
	public Boolean checkClassroomAvailability(List<TimetableFields> timetableFiedls, Classroom salle,String day, int starthour) {

		for(int i=0; i<timetableFiedls.size();i++) {
			if((timetableFiedls.get(i) ).getSalle().getIdSalle() == salle.getIdSalle()) {
				if((timetableFiedls.get(i) ).getDay().equals(day)) {
					if((timetableFiedls.get(i) ).getStartHour()== starthour) {
						return false;
					}
				}
			}
		}
		
		
		return true;
	}
     public Boolean checkTeacherAvailability(List<TimetableFields> timetableFiedls, Teacher teacher,String day,
    		 int starthour) {
    	 for(int i=0; i<timetableFiedls.size();i++) {
 			if((timetableFiedls.get(i) ).getTeacher().getIdPerson() == teacher.getIdPerson()) {
 				if((timetableFiedls.get(i) ).getDay().equals(day) ) {
 					if((timetableFiedls.get(i) ).getStartHour()== starthour) {
 						return false;
 					}
 				}
 			}
 		}
 		
    	 return true;
     }
     
     public Boolean save(Classroom salle, Teacher teacher, Timetable timetable,Subject subject, String day,int startHour,List<TimetableFields> timetableFiedls) {
    	 
    	 if(checkTeacherAvailability(timetableFiedls,teacher,day,startHour) && checkClassroomAvailability(timetableFiedls,salle,day, startHour)) {
    		 TimetableFields timetabField= new TimetableFields();
    		 timetabField.setDay(day);
    		 timetabField.setSalle(salle);
    		 timetabField.setStartHour(startHour);
    		 timetabField.setTeacher(teacher);
    		 timetabField.setSubject(subject);
    		 timetabField.setTimetable(timetable);
    		 timetabFieldsRepo.save(timetabField);
    		 return true;
    		 }
    	return false;
     }
     
     public void deleteField(Long id) {
    	 timetabFieldsRepo.deleteById(id);
     }
     
     
     
}
