package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component("basketCoachBean")
public class BasketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice dunks";
    }

}
