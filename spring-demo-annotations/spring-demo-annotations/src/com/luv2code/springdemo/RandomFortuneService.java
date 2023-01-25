package com.luv2code.springdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
    @Value("${fortunes.good}")
    private String goodFortune;

    @Value("${fortunes.bad}")
    private String badFortune;

    private List<String> fortunes;

    private RandomFortuneService() {
    }

    @PostConstruct
    public void InitFortunes(){
        String filename= "C:/Users/AlbarnSoul/Repos/Spring-Hibernate-for-Beginners/spring-demo-annotations/spring-demo-annotations/src/com/luv2code/springdemo/fortunes.txt";
        File file = new File(filename);

        System.out.println("Reading "+ filename);
        System.out.println("File exists: " + file.exists());
        fortunes = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(
            
        new FileReader(file))) {
			String fortune;
			while ((fortune = br.readLine()) != null) {
                fortunes.add(fortune);
			}
        } catch (IOException e) {
			e.printStackTrace();
		}

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
