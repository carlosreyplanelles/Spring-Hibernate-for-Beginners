package com.luv2code.springdemo.mvc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

	public String value() default "LUV";
	public String message() default "Course code must start with LUV";
	
	//define the groups of errors that are related to this Validation annotation
	public Class<?>[] groups() default {};
	
	//define payloads
	public Class<? extends Payload>[] payload() default {};
}
