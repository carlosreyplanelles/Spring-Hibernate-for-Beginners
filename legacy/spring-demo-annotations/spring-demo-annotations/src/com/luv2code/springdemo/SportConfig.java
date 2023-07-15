package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.luv2code.springdemo")
@PropertySource("classpath:sport.properties")
public class SportConfig {

    //Define method for fortuneService bean 
    @Bean
    public FortuneService badfortuneService(){
        return new BadFortuneService();
    }

    //Define swimCoach Bean and inject the dependency
    @Bean
    public Coach swimCoach(){
        return new SwimCoach(badfortuneService());
    }
    
}
