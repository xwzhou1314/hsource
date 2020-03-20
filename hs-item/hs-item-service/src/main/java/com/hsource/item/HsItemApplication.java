package com.hsource.item;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableMethodCache(basePackages = "com.hsource.item")
@EnableCreateCacheAnnotation
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class HsItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(HsItemApplication.class);
    }
}
