package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class funRestController {

    //Inject coach.name and coach.team from properties file.
    @Value("${coach.name}")
    private String name;

    @Value("${coach.team}")
    private String team;


    @GetMapping("/getCoachInfo")
    public String getCoach(){
        return "Coach: "+ name + " team: "+ team;
    }

    
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("/workout")
    public String getWorkout(){
        return "Run a hard 5k";
    }
}
