package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class BadFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "Today is not your day!";
    }

}
