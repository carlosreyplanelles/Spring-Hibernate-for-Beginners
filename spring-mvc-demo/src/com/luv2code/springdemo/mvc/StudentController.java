package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@RequestMapping("/form")
	public String showForm(Model model) {
		
		Student s = new Student();
		
		model.addAttribute("student", s);
		
		return "student-form";
	}
	
	@RequestMapping("/confirmation")
	public String processForm(@ModelAttribute("student") Student student) {
		return "registration-confirmation";
	}
	
}
