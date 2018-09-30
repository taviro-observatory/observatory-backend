package hu.taviro.observatory.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("hu.taviro.observatory.backend")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
