package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Practice4SpringApp { // Черномуров Семён Андреевич ИКБО-10-21
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Practice4SpringApp.class, args);

        ctx.getBean(RSocketServerApp.class).createServer();
        ctx.getBean(RSocketClientApp.class).createClient();

        ctx.getBean(H2DbEntityRepository.class).findAll().forEach(entity -> {
            System.out.printf("entity from h2 db: id: %d; payload: %s%n", entity.getId(), entity.getPayload());
        });
    }
}
