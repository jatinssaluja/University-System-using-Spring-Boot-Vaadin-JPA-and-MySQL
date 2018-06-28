package com.jatin.universitysystem.service.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.universitysystem.model.entity.University;
import com.jatin.universitysystem.repository.university.UniversityRepository;

@Service
public class AddUniversityServiceImpl implements AddUniversityService{

	@Autowired
	private UniversityRepository universityRepository;

	@Override
	public void saveUniversity(University universityDAO) {
		
		University university = new University();
		university.setUniversityName(universityDAO.getUniversityName());
		university.setUniversityCountry(universityDAO.getUniversityCountry());
		university.setUniversityCity(universityDAO.getUniversityCity());
		
		
		universityRepository.save(university);
		
	}

}
