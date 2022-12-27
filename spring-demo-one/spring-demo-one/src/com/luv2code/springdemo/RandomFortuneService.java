package com.luv2code.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {
    String[] fortunes;
    Random random;

    public RandomFortuneService() {
        this.fortunes = new String[3];
        this.fortunes[0] = "Today is your lucky day";
        this.fortunes[1] = "Today is an average day";
        this.fortunes[2] = "Today is a bad day";
        this.random = new Random();
    }

    public String getFortune() {
        int fortune = random.nextInt(this.fortunes.length);
        return fortunes[fortune];
    }
}
