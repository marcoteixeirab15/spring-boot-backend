package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Cidade;
import com.marcoteixeira.cursomc.repositories.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public List<Cidade> findByEstado(Integer id){
        return cidadeRepository.findCidades(id);
    }

}
