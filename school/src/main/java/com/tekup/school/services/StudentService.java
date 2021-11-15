package com.tekup.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tekup.school.entities.Student;
import com.tekup.school.repository.ClasseRepository;
import com.tekup.school.repository.StudentRepository;


@Service
public class StudentService {

	
@Autowired
ClasseRepository classerepo;
@Autowired
StudentRepository studentrepo;
	
	public Page<Student> findPaginatedListStudentsByClass(Long id,int pageNo, int pageSize, String sortField, String sortDirection){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
			 Sort.by(sortField).descending();
		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
		return this.studentrepo.findByClasse(classerepo.findById(id), pageable);
	}


	
	
}
