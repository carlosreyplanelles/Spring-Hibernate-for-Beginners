package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {
    
    public static void main (String[] args){

        //Load Config
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //Retrieve Bean
        Coach coach = context.getBean("tennisCoach", Coach.class);
        Coach coachCopy = context.getBean("tennisCoach", Coach.class);


        //check both are equal
        boolean equals = coach == coachCopy;
        System.out.println("Point to the same object:" + equals);
        System.out.println("coach Mem location: " + coach);
        System.out.println("coachCopy Mem location: " + coachCopy);

        //close Context
        context.close();
        
    }
}
