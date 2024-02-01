package com.zpo.studentsystem.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.zpo.studentsystem.model")
@EnableJpaRepositories("com.zpo.studentsystem.repository")
@Configuration
public class ConfigurationJPA {
}
