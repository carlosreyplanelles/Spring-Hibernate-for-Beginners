package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    private Coach demoCoach;

    @Autowired
    public void setDemoCoach(Coach coach){
        demoCoach = coach;
    }

    @GetMapping("/getWorkout")
    public String getDailyWorkout(){
        return demoCoach.getDailyWorkout();
    }
}
