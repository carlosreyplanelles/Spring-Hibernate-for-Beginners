package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class FunnyFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "Today you are going to laugh out loud with your beloved ones";
    }

}
