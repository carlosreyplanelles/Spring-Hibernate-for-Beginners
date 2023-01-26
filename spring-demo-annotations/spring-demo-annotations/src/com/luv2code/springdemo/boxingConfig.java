package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.luv2code.springdemo")
public class boxingConfig {

    @Bean
    public FortuneService funnyFortuneService(){
        return new FunnyFortuneService();
    }

    @Bean
    public BoxingCoach boxingCoach(){
        return new BoxingCoach(funnyFortuneService());
    }
    
}
