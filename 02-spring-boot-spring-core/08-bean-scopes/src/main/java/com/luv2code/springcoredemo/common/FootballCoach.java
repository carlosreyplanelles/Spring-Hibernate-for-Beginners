package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach{

    public FootballCoach(){
        System.out.println("In Constructor: "+ getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Run 20km!!!!";
    }
}
