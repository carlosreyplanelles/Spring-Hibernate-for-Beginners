package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemo {

    public static void main(String[] Args) {
        // read Spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
        // get the bean from the Spring Container
        SwimCoach coach = context.getBean("swimCoach", SwimCoach.class);
        // call the method from the bean
        System.out.println("SwimCoach workout: " + coach.getDailyWorkout());
        System.out.println(coach.getTeam());
        // Close context
        context.close();
    }

}
