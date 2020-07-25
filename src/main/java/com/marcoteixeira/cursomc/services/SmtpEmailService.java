package com.marcoteixeira.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService{

    private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        log.info("Enviando email...");
        mailSender.send(simpleMailMessage);
        log.info("Email enviado.");
    }

    @Override
    public void sendHtmlEmail(MimeMessage mimeMessage) {
        log.info("Enviando email...");
        javaMailSender.send(mimeMessage);
        log.info("Email enviado.");
    }

}
