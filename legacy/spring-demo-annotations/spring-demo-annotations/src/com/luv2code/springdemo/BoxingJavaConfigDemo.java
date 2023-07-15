package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BoxingJavaConfigDemo {

    public static void main(String[] Args) {
        // read Spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(boxingConfig.class);
        // get the bean from the Spring Container
        Coach coach = context.getBean("boxingCoach", Coach.class);
        // call the method from the bean
        System.out.println(coach.getDailyWorkout());
        System.out.println(coach.getDailyFortune());
        // Close context
        context.close();

    }

}
