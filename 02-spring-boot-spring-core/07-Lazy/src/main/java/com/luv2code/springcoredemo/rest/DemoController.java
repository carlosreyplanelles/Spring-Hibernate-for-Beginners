package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach demoCoach;

    @Autowired
    public DemoController(@Qualifier("baseballCoach")Coach coach){
        System.out.println("In Constructor: "+ getClass().getSimpleName());
        demoCoach = coach;
    }

    @GetMapping("/getWorkout")
    public String getDailyWorkout(){
        return demoCoach.getDailyWorkout();
    }
}
