package com.jatin.universitysystem.service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.universitysystem.model.entity.Student;
import com.jatin.universitysystem.repository.student.StudentRepository;

@Service
public class ShowAllStudentsServiceImpl implements ShowAllStudentsService {
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> showAllStudents() {
		
		return studentRepository.getAllStudents();
	}

}
