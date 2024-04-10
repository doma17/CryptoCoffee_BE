package inu.spp.cryptocoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CryptoCoffeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCoffeeApplication.class, args);
    }

}
