package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Estado;
import com.marcoteixeira.cursomc.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> findAll(){
        return estadoRepository.findAllByOrderByNome();
    }

}
