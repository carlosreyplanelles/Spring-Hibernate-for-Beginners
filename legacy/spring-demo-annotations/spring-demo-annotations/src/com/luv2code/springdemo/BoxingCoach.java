package com.luv2code.springdemo;

public class BoxingCoach implements Coach{

    private FortuneService fortuneService;

    public BoxingCoach(FortuneService theFortuneService){
        fortuneService = theFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run meters at 70% as warmup";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
    
}
