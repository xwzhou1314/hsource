package com.hsource.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author bystander
 * @date 2018/9/13
 */
@SpringBootApplication
@EnableEurekaClient
public class HsItemService {

    public static void main(String[] args) {
        SpringApplication.run(HsItemService.class);
    }
}
