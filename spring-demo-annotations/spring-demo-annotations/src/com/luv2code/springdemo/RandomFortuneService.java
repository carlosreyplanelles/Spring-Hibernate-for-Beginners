package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
    @Value("${fortunes.good}")
    private String goodFortune;

    @Value("${fortunes.bad}")
    private String badFortune;

    private RandomFortuneService() {
    }

    @Override
    public String getFortune() {
        String[] fortunes = {
                this.goodFortune,
                this.badFortune
        };
        Random rand = new Random();
        int index = rand.nextInt(fortunes.length);
        return fortunes[index];
    }

}
