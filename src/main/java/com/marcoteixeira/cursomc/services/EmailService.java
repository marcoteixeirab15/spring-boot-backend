package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage simpleMailMessage);

    void sendOrderConfirmationHtmlEmail(Pedido pedido);

    void sendHtmlEmail(MimeMessage mimeMessage);

}
