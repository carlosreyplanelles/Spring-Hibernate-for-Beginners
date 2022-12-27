package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {
    public static void main(String args[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "beanLifeCycle-applicationContext.xml");
        Coach coach = context.getBean("coachBean", Coach.class);

        System.out.println(coach.getDailyWorkout());

        context.close();
    }
}
