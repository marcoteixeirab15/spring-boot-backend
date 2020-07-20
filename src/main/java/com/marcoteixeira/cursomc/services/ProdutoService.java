package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Categoria;
import com.marcoteixeira.cursomc.domain.Produto;
import com.marcoteixeira.cursomc.repositories.CategoriaRepository;
import com.marcoteixeira.cursomc.repositories.ProdutoRepository;
import com.marcoteixeira.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto find(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado id: " + id + ", Tipo: " + Produto.class.getName())
        );
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderby, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderby);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.search(nome, categorias, pageRequest);

    }

}
