package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

	@RequestMapping("/helloForm")
	public String showForm() {
		
		return "helloWorld-form";
	}
	
	//Process Form adding parameters to the request
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloWorld";
	}
	
	//Process form adding attributes to the model
	@RequestMapping("/processFormShout")
	public String processFormShout(HttpServletRequest req, Model model) {
		
		String name = req.getParameter("studentName");
		
		String msg = "YO! " + name.toUpperCase(); 
		
		model.addAttribute("msg", msg);
		
		return "helloWorld";
	}
	
	//Processs form adding attributes to the model. Params retrieved by @RequestParam
	@RequestMapping("/processFormRequestParam")
	public String processFormShout(@RequestParam("studentName") String studentName,  
			Model model) {
		
		String msg = "YO! " + studentName.toUpperCase(); 
		
		model.addAttribute("msg", msg);
		
		return "helloWorld";
	}
}
