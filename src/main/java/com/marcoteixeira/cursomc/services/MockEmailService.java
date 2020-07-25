package com.marcoteixeira.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;


public class MockEmailService extends AbstractEmailService{

    private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        log.info("Simulando envio de email......");
        log.info(simpleMailMessage.toString());
        log.info("Email enviado.");
    }

    @Override
    public void sendHtmlEmail(MimeMessage mimeMessage) {
        log.info("Simulando envio de email HTML......");
        log.info(mimeMessage.toString());
        log.info("Email enviado.");
    }
}
