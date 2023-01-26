package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    //Init method
    @PostConstruct
    public void postStart(){
        System.out.println(">>TennisCoach: Inside postStart()");
    }

    //Destroy Method
    @PreDestroy
    public void preDestroy(){
        System.out.println(">>TennisCoach: Inside preDestroy()");
    }
    
    @Autowired
    private TennisCoach(@Qualifier("randomFortuneService") FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Practice backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
