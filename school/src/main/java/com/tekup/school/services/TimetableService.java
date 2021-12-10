package com.tekup.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tekup.school.entities.Classe;
import com.tekup.school.entities.Timetable;
import com.tekup.school.repository.TimetableRepository;

@Service
public class TimetableService {
  @Autowired
  TimetableRepository timetabRepo;
  
  
  public Page<Timetable> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.timetabRepo.findAll(pageable);
	}
  
  public Timetable getById(Long id) {
	  return timetabRepo.getById(id);
  }
	
}
