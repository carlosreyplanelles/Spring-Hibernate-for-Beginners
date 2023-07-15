package com.luv2code.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>{
	
	
	private String coursePrefix;
	@Override
	public void initialize(CourseCode courseCode) {
		// TODO Auto-generated method stub
		coursePrefix = courseCode.value();
	}

	@Override
	public boolean isValid(String userCourse, ConstraintValidatorContext arg1) {
		boolean result = true;
		
		if (userCourse != null) {
			result = userCourse.startsWith(coursePrefix);
		}
		return result;
	}
	
	

}
