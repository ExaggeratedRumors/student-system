package com.zpo.studentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



//@EntityScan("com.zpo.studentsystem.model")
//@EnableJpaRepositories("com.zpo.studentsystem") //Jeżeli mamy w innych katalogach to trzeba pathing włączyć
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class StudentSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentSystemApplication.class, args);
        System.out.println("Siema");
    }

}
