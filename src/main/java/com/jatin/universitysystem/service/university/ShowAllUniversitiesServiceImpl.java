package com.jatin.universitysystem.service.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.universitysystem.model.entity.University;
import com.jatin.universitysystem.repository.university.UniversityRepository;

@Service
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService{
	

	@Autowired
	UniversityRepository universityRepository;

	

	@Override
	public List<University> showAllUniversities() {
	
		return universityRepository.getAllUniversities();
	}

}
