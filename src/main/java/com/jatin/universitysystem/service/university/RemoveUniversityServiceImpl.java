package com.jatin.universitysystem.service.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.universitysystem.model.entity.University;
import com.jatin.universitysystem.repository.university.UniversityRepository;

@Service
public class RemoveUniversityServiceImpl implements RemoveUniversityService{
	
	@Autowired
	private UniversityRepository universityRepository;

	@Override
	public void removeUniversity(University university) {
		
		universityRepository.delete(university);
		
	}
	
	

}
