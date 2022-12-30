package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

    public static void main(String[] Args) {
        // read Spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // get the bean from the Spring Container
        Coach coach = context.getBean("tennisCoach", Coach.class);
        Coach basketCoach = context.getBean("basketCoachBean", Coach.class);
        // call the method from the bean
        System.out.println("TennisCoach workout: " + coach.getDailyWorkout());
        System.out.println("BasketCoach workout: " + basketCoach.getDailyWorkout());
        // Close context
        context.close();
    }

}
