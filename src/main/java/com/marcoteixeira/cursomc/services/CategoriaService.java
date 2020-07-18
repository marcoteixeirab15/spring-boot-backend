package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Categoria;
import com.marcoteixeira.cursomc.repositories.CategoriaRepository;
import com.marcoteixeira.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria buscar(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado id: " + id + ", Tipo: " + Categoria.class.getName())
        );
    }

    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

}
