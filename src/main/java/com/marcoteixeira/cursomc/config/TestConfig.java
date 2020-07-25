package com.marcoteixeira.cursomc.config;

import com.marcoteixeira.cursomc.services.DbService;
import com.marcoteixeira.cursomc.services.EmailService;
import com.marcoteixeira.cursomc.services.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    private final DbService dbService;

    public TestConfig(DbService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}
