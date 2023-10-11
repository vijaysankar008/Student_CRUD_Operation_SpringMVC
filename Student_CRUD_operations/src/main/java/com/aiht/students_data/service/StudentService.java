package com.aiht.students_data.service;

import java.util.List;
import java.util.Optional;

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

    public Student getStudentById(int id) {
    	return studentRepository.findById(id).get();
    }
    
    public void deleteData(int id) {
    	studentRepository.deleteById(id);
    }

    public void updateStudentData(int id, Student updatedStudent) {
        // Retrieve the existing student by ID
        Optional<Student> existingStudent = studentRepository.findById(id);
        
        if (existingStudent.isPresent()) {
            Student studentToUpdate = existingStudent.get();
            
            // Update the student's properties with the new data
            studentToUpdate.setName(updatedStudent.getName());
            studentToUpdate.setCourse(updatedStudent.getCourse());
            studentToUpdate.setFees(updatedStudent.getFees());

            // Save the updated student to the database
            studentRepository.save(studentToUpdate);
        } else {
            // Handle the case where the student with the given ID is not found
            // You can throw an exception or handle it in a way that suits your application
        }
    }
}
