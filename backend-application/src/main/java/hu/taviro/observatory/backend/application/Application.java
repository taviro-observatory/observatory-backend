package hu.taviro.observatory.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("hu.taviro.observatory.backend")
@EnableMongoRepositories("hu.taviro.observatory.backend.core")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
