package com.hsource.gateway.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "hs.filter")
public class FilterProperties {
    private List<String> allowPaths;
}
