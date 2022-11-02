package com.springboot.hello01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Hello01Application {

    public static void main(String[] args) {
        SpringApplication.run(Hello01Application.class, args);
    }

}
