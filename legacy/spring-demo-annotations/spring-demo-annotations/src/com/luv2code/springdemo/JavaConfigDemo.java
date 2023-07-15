package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemo {

    public static void main(String[] Args) {
        // read Spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
        // get the bean from the Spring Container
        Coach coach = context.getBean("tennisCoach", Coach.class);
        Coach basketCoach = context.getBean("basketCoachBean", Coach.class);
        // call the method from the bean
        System.out.println("TennisCoach workout: " + coach.getDailyWorkout());
        System.out.println("BasketCoach workout: " + basketCoach.getDailyWorkout());
        System.out.println(basketCoach.getDailyFortune());
        // Close context
        context.close();
    }

}
