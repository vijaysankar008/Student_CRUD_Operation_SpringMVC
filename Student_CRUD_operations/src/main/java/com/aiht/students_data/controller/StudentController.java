package com.aiht.students_data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aiht.students_data.domain.Student;
import com.aiht.students_data.service.StudentService;

@Controller
@RequestMapping("/studentDatabase")
public class StudentController {

	@Autowired
	public StudentService studentService;

	// This method is for saveData

	@GetMapping("/home")
	public String homePage(Model model) {
		List<Student> studentsList = studentService.listAll();
		model.addAttribute("studentsList", studentsList);
		return "Index";
	}

	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("student", new Student());
		return "NewData";// return to the NewData HTML page with the Student Object
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudentData(student);// It will store the data to the database
		return "redirect:/studentDatabase/home";
	}

	// Display the form to edit a student's data
	@GetMapping("/edit/{id}")
	public String showEditStudentPage(@PathVariable(name = "id") int id, Model model) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "editStudent"; // Display the edit form
	}

	// Handle the form submission to update a student's data
	@PostMapping("/edit/{id}")
	public String updateStudentData(@PathVariable(name = "id") int id, @ModelAttribute Student student) {
		studentService.updateStudentData(id, student);
		return "redirect:/studentDatabase/home"; // Redirect to a student list page or any other appropriate page
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable(name = "id") int id) {
		studentService.deleteData(id);
		return "redirect:/studentDatabase/home";
	}
}
