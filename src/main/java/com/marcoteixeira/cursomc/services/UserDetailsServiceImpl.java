package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Cliente;
import com.marcoteixeira.cursomc.repositories.ClienteRepository;
import com.marcoteixeira.cursomc.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public UserDetailsServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfil());
    }
}
