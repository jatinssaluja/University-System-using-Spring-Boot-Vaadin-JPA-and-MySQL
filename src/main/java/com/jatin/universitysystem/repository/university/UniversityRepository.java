package com.jatin.universitysystem.repository.university;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jatin.universitysystem.model.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer>{
	
	@Query("select s from University s order by s.universityName")
	List<University> getAllUniversities();
	
	@Query("select count(s) from Student s where s.university.id=:universityId")
	Integer getNumberOfStudents(@Param("universityId") Integer universityId);


}
