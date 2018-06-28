package com.jatin.universitysystem.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.universitysystem.model.entity.Student;
import com.jatin.universitysystem.repository.student.StudentRepository;

@Service
public class RemoveStudentServiceImpl implements RemoveStudentService{
	
	@Autowired
	StudentRepository studentRepository;
	

	@Override
	public void removeStudent(Student student) {
		
		studentRepository.delete(student);
		
	}

}
