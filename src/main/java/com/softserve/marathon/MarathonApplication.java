package com.softserve.marathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MarathonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarathonApplication.class, args);
    }

}
