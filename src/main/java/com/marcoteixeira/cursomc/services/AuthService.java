package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Cliente;
import com.marcoteixeira.cursomc.repositories.ClienteRepository;
import com.marcoteixeira.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    private Random random = new Random();

    public AuthService(ClienteRepository clienteRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.clienteRepository = clienteRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    public void sendNewPassword(String email){
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw  new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        cliente.setSenha(bCryptPasswordEncoder.encode(newPass));

        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);


    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i<10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if(opt == 0 ){ //gera digito
            return (char) (random.nextInt(10) + 48);
        }else if(opt == 1 ){//gera Letra Maiuscula
            return (char) (random.nextInt(26) + 65);
        }else {//gera letra minuscula
            return (char) (random.nextInt(26) + 97);
        }

    }
}
