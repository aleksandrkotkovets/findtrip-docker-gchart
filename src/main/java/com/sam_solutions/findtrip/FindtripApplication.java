package com.sam_solutions.findtrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FindtripApplication {

   public static void main(String[] args) {
        SpringApplication.run(FindtripApplication.class, args);
    }

}

