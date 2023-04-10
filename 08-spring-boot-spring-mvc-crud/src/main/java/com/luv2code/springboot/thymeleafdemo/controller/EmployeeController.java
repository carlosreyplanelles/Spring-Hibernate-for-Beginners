package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeServiceBean){
		employeeService = employeeServiceBean;
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// add to the spring model
		theModel.addAttribute("employees", employeeService.findAll());

		return "employees/list-employees";
	}

	@GetMapping("/formAddEmployee")
	public String showAddEmployeeForm(Model model){
		Employee e = new Employee();
		model.addAttribute("employee", e);

		return "employees/add-employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee newEmployee){
		employeeService.save(newEmployee);

		return "redirect:/employees/list";
	}

	@GetMapping("/formUpdateEmployee")
	public String showUpdateForm(@RequestParam("employeeId") int empId, Model model){
		Employee e = employeeService.findById(empId);
		model.addAttribute("employee", e);

		return "employees/update-employee-form";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int empId){
		employeeService.deleteById(empId);

		return "redirect:/employees/list";
	}
}









