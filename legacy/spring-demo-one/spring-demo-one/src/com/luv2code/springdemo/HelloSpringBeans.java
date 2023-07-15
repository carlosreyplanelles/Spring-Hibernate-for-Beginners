package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringBeans {

    public static void main(String[] args) throws Exception {
        // load Spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // retrieve a bean from spring container
        Coach coach = context.getBean("coachBean", Coach.class);
        // call workoutBeanMethod
        System.out.println(coach.getDailyWorkout());
        // call fortune Injected Fortune service method
        System.out.println(coach.getDaylyFortune());
        // close context
        context.close();
    }

}
