package com.zpo.studentsystem;

import com.zpo.studentsystem.repository.StudentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zpo.studentsystem.model.*;
import org.springframework.boot.CommandLineRunner;


@EntityScan("com.zpo.studentsystem.model")
@EnableJpaRepositories("com.zpo.studentsystem.repository") //Jeżeli mamy w innych katalogach to trzeba pathing włączyć
@SpringBootApplication
public class StudentSystemApplication {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public static void main(String[] args) {
        SpringApplication.run(StudentSystemApplication.class, args);
        System.out.println("Siema");
    }

//    @Bean
//    CommandLineRunner start(StudentRepository studentRepository) {
//        return args -> {
//            Student student = new Student(1L, "test", "test1", 1L);
//            studentRepository.save(student);
//            studentRepository.findAll().forEach(p -> log.info("person: {}", p));
//        };
//    }

}
