package com.aiht.students_data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aiht.students_data.domain.Student;
import com.aiht.students_data.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	public StudentService studentService;

	// This method is for saveData

	@GetMapping("/home")
	public String homePage(Model model) {
		List<Student> studentsList = studentService.listAll();
		model.addAttribute("studentsList", studentsList);
		// System.out.print("Get / ");
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
		return "redirect:/home";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
		ModelAndView view = new ModelAndView("new");
		Student student = studentService.editStudentData(id);
		view.addObject("student", student);
		return view;
	}
      
	@RequestMapping("/delete/{id}")
	public String deleteStudent(@PathVariable(name="id") int id) {
		studentService.deleteData(id);
		return "redirect:/home";
	}
}
