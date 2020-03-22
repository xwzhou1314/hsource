package com.hsource.common.mybatiesplus.sqlserver;

import org.springframework.context.annotation.Bean;

/**
 * @author xwzhou
 * @date 2020-03-21 15:46
 */
// @Configuration
public class MapperConfig {

    @Bean
    public NoahSqlInjector logicSqlInjector() {
        return new NoahSqlInjector();
    }

}