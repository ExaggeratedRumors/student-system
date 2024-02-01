package com.zpo.studentsystem;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.zpo.studentsystem"})
@Configuration
public class ConfigurationJPA {
}
