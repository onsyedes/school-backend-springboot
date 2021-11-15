package com.tekup.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tekup.school.entities.Classe;
import com.tekup.school.repository.ClasseRepository;

@Service
public class ClassService {

	@Autowired
	ClasseRepository classerepo;

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
}
