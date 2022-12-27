package com.luv2code.springdemo;

public class BaseballCoach implements Coach {

    private FortuneService fortuneService;

    public BaseballCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "30 minutes batting practice";
    }

    @Override
    public String getDaylyFortune() {
        return fortuneService.getFortune();
    }

    // Add init bean Method
    public void StartrtupBeanMethod() {
        System.out.println("Bean initzialization");
    }

    // Add destroy bean Method
    public void destroyBeanMethod() {
        System.out.println("Bean Destruction");
    }
}
