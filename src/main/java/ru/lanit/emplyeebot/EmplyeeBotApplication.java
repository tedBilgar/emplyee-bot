package ru.lanit.emplyeebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class EmplyeeBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmplyeeBotApplication.class, args);
    }

}
