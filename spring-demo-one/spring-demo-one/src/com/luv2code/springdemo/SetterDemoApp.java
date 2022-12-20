package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

    public static void main(String args[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        FootballCoach coach = context.getBean("footballCoachBean", FootballCoach.class);

        System.out.println(coach.getDailyWorkout());
        System.out.println(coach.getDaylyFortune());
        System.out.println(coach.getEmail());
        System.out.println(coach.getTeam());

        context.close();
    }
}
