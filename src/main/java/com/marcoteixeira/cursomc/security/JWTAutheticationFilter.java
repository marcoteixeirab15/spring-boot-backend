package com.marcoteixeira.cursomc.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcoteixeira.cursomc.dto.CredenciaisDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAutheticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAutheticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {

        CredenciaisDTO credenciaisDTO = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(), credenciaisDTO.getSenha());

        Authentication auth = authenticationManager.authenticate(authToken);
        return auth;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                        FilterChain chain, Authentication auth) throws IOException, ServletException {

        String username = ((UserSS) auth.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
    }

}
