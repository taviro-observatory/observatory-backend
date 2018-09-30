package hu.taviro.observatory.backend.application.migrations;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MongobeeConfiguration {

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;

    public MongobeeConfiguration(@Value("${spring.data.mongodb.host}") String host,
                                 @Value("${spring.data.mongodb.port}") String port,
                                 @Value("${spring.data.mongodb.database}") String database,
                                 @Value("${spring.data.mongodb.username}") String username,
                                 @Value("${spring.data.mongodb.password}") String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    @Bean
    public Mongobee mongobee() {
        String mongoUri = String.format("mongodb://%s:%s@%s:%s/%s", username, password, host, port, database);
        Mongobee runner = new Mongobee(mongoUri);
        runner.setChangeLogsScanPackage("hu.taviro.observatory.backend.application.migrations");
        return runner;
    }
}
