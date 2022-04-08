package com.olim.cvhelper.bot.util;

import com.olim.cvhelper.backoffice.service.UserRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        return args -> {
            System.out.println(passwordEncoder.encode("Olim228!"));
        };
    }

}