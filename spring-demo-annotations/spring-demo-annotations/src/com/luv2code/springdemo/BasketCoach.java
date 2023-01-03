package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("basketCoachBean")
public class BasketCoach implements Coach {

    @Autowired
    @Qualifier("randomFortuneService")
    FortuneService fortuneService;

    public BasketCoach() {
    }

    /*
     * @Autowired
     * 
     * @Qualifier("HappyFortuneService")
     * public void setFortuneService(FortuneService fortuneService) {
     * this.fortuneService = fortuneService;
     * }
     */

    @Override
    public String getDailyWorkout() {
        return "Practice dunks";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
