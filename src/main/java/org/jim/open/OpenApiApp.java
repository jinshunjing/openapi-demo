package org.jim.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OpenApiApp {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiApp.class, args);
    }

}
