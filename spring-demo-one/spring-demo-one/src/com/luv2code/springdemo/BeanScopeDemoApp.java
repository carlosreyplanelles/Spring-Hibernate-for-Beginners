package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {
    public static void main(String args[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
        Coach coach = context.getBean("coachBean", Coach.class);
        Coach coach2 = context.getBean("coachBean", Coach.class);

        System.out.println(coach == coach2);

        context.close();
    }
}
