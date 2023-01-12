package com.example.coindesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.coindesk.respository")
@EntityScan(basePackages = "com.example.coindesk.entity")
public class CoindeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoindeskApplication.class, args);
    }

}
