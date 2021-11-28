package com.tekup.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.SchoolTeaching;
import com.tekup.school.repository.ClasseRepository;
import com.tekup.school.repository.SchoolTeachingRepository;
import com.tekup.school.repository.StudentRepository;

@Service
public class ClassService {

	@Autowired
	ClasseRepository classerepo;
	@Autowired
	StudentRepository studentrepo;
	@Autowired
	SchoolTeachingRepository schoolTeachRepo;

	public Page<Classe> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.classerepo.findAll(pageable);
	}

	// Add a new class
	public void addClass(Classe classe) {

		classerepo.save(classe);
	}
	//update a class
	
	public void updateClass(Classe classe) {
		classerepo.save(classe);
	}
	//Delete a class
	public void deleteClass(Long id) {
		classerepo.deleteById(id); }
	// Delete schoolTeaching 
	public void deleteSchoolTeachingField(Long id) {
		  schoolTeachRepo.deleteById(id);
		
		
	}
	
	
}
