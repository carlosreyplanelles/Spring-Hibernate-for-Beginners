package com.luv2code.springdemo.mvc;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

@Component
public class Student {
	
	private String firstName;
	private String lastName;
	private String country;
	private String favoriteLanguage;
	private String[] userOS;
	private LinkedHashMap<String, String> favoriteLanguageOptions;
	private LinkedHashMap<String, String> operatingSystems;

	public Student() {
		
		favoriteLanguageOptions = new LinkedHashMap<>();
		favoriteLanguageOptions.put("Java", "Java");
		favoriteLanguageOptions.put("C#", "C#");
		favoriteLanguageOptions.put("Python", "Python");
		
		operatingSystems= new LinkedHashMap<>();
		operatingSystems.put("Linux", "Linux");
		operatingSystems.put("Windows", "Windows");
		operatingSystems.put("Mac", "Mac");
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public LinkedHashMap<String, String> getFavoriteLanguageOptions() {
		return favoriteLanguageOptions;
	}

	public LinkedHashMap<String, String> getOperatingSystems() {
		return operatingSystems;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public String[] getUserOS() {
		return userOS;
	}

	public void setUserOS(String[] userOS) {
		this.userOS = userOS;
	}
}
