package com.aiht.students_data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiht.students_data.domain.Student;
import com.aiht.students_data.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	public StudentRepository studentRepository;

	// CRUD operations Methods

	public void saveStudentData(Student student) {
		studentRepository.save(student);
	}

	public List<Student> listAll() {
		return studentRepository.findAll();
	}

    public Student editStudentData(int id) {
    	return studentRepository.findById(id).get();
    }
    
    public void deleteData(int id) {
    	studentRepository.deleteById(id);
    }
}
