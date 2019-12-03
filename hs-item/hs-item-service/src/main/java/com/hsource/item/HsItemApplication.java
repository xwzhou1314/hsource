package com.hsource.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HsItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(HsItemApplication.class);
    }
}
