package hu.taviro.observatory.backend.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class HelloController {

    @GetMapping("hello")
    public String getGreeting() {
        return "Hello World!";
    }
}
