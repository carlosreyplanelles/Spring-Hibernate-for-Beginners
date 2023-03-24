package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {


    public BaseballCoach(){
        System.out.println("In Constructor: "+ getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

    //Init method
    @PostConstruct
    public void initialization(){
        System.out.println("Post contruct: "+ getClass().getSimpleName());
    }

    //Destroy Method
    @PreDestroy
    public void dataCleanup(){
        System.out.println("Pre destroy: "+ getClass().getSimpleName());
    }
}
