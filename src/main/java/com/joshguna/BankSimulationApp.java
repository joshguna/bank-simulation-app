package com.joshguna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankSimulationApp {

    public static void main(String[] args) {
        SpringApplication.run(BankSimulationApp.class, args);
    }

    @Bean
    public String str1() {
        return "Joshgun Bank";
    }

}
