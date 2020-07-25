package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);
    void sendEmail(SimpleMailMessage simpleMailMessage);

}
