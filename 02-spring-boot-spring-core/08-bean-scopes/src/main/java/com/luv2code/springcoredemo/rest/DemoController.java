package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach demoCoach;
    private Coach secondDemoCoach;

    @Autowired
    public DemoController(@Qualifier("baseballCoach")Coach coach,
                          @Qualifier("baseballCoach")Coach secondCoach){
        System.out.println("In Constructor: "+ getClass().getSimpleName());
        demoCoach = coach;
        secondDemoCoach=secondCoach;
    }
    @GetMapping("/getWorkout")
    public String getDailyWorkout(){
        return demoCoach.getDailyWorkout();
    }

    @GetMapping("/checkEqual")
    public String checkEqual(){
        /* Default bean scope is singletone. With singletone the bean is created only once and the same object instance is always injected. in this case the comparison will return true
        If the bean scope is prototype will create an instance each time the dependency is injected. In this case it will return false. */
        return "Comparing demoCoach and secondDemoCoach: " + (demoCoach==secondDemoCoach);
    }
}
