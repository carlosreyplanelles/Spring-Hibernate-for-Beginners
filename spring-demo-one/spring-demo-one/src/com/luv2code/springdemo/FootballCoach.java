package com.luv2code.springdemo;

public class FootballCoach implements Coach {

    private FortuneService fortuneService;
    private String email;
    private String team;

    public FootballCoach() {
    }

    public void setFortuneService(FortuneService fortuneServiceSetterBean) {
        this.fortuneService = fortuneServiceSetterBean;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getEmail() {
        return email;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String getDailyWorkout() {
        return "Score 25 goals to the goal keeper.";
    }

    @Override
    public String getDaylyFortune() {
        return fortuneService.getFortune();
    }

}
