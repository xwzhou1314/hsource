package com.hsource;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableMethodCache(basePackages = "com.hsource")
@EnableCreateCacheAnnotation
@SpringBootApplication
@EnableDiscoveryClient
public class HsUserApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(HsUserApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HsUserApplication.class);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }
}
