package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.core.DatabaseClient;

@SpringBootApplication
public class Main { // Черномуров Семён Андреевич ИКБО-10-21

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(DatabaseClient client) {
        return args -> {
            client.sql("CREATE TABLE IF NOT EXISTS beer_mugs (id BIGINT AUTO_INCREMENT PRIMARY KEY,user_id BIGINT,mug_status VARCHAR(50),ready_time TIMESTAMP);CREATE TABLE IF NOT EXISTS users_users (id BIGINT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255));")
                    .then().block();
        };
    }
}
