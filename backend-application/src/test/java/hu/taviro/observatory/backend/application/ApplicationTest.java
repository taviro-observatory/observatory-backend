package hu.taviro.observatory.backend.application;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Test
    public void applicationStarts() {
        Application.main(new String[] {});
    }
}