package com.hsource.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HsRegistry {
    public static void main(String[] args) {
        SpringApplication.run(HsRegistry.class);
    }
}
