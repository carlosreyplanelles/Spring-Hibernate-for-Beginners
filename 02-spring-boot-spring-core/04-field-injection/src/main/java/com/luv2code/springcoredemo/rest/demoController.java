package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    @Autowired
    private Coach demoCoach;

    @GetMapping("/getWorkout")
    public String getDailyWorkout(){
        return demoCoach.getDailyWorkout();
    }
}
