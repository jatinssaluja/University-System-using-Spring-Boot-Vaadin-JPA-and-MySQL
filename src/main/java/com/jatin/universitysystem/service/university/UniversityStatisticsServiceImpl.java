package com.jatin.universitysystem.service.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.universitysystem.repository.university.UniversityRepository;

@Service
public class UniversityStatisticsServiceImpl implements UniversityStatisticsService {
	
	@Autowired
	private UniversityRepository universityRepository;
	

	@Override
	public Integer getUniversityStatistics(Integer universityId) {
		
	
		return universityRepository.getNumberOfStudents(universityId);
	}
	
	

}
