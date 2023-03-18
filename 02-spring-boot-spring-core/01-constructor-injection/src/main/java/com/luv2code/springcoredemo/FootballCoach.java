package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Run 20km!!!!";
    }
}
