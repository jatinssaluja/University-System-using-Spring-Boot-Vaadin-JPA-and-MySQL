package com.jatin.universitysystem.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.universitysystem.model.entity.Student;
import com.jatin.universitysystem.repository.student.StudentRepository;

@Service
public class AddStudentServiceImpl implements AddStudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void saveStudent(Student studentDAO) {
		
		Student student = new Student();
		student.setFirstName(studentDAO.getFirstName());
		student.setLastName(studentDAO.getLastName());
		student.setAge(studentDAO.getAge());
		student.setGender(studentDAO.getGender());
		student.setUniversity(studentDAO.getUniversity());
		
		studentRepository.save(student);
		
	}
	
	

}
