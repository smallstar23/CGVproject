package com.koreait.cgvproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CgVprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CgVprojectApplication.class, args);
    }

}
