package com.marcoteixeira.cursomc.config;

import com.marcoteixeira.cursomc.services.DbService;
import com.marcoteixeira.cursomc.services.EmailService;
import com.marcoteixeira.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    private final DbService dbService;


    public ProdConfig(DbService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!strategy.equals("create")) {
            return false;
        }

        dbService.instantiateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}
