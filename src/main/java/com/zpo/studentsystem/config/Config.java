package com.zpo.studentsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public AccessLog accessLog() {
        return new AccessLog();
    }
}
