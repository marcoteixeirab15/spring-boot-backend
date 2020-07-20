package com.marcoteixeira.cursomc.resources;

import com.marcoteixeira.cursomc.domain.Produto;
import com.marcoteixeira.cursomc.dto.ProdutoDTO;
import com.marcoteixeira.cursomc.resources.utils.URL;
import com.marcoteixeira.cursomc.services.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    private final ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {

        Produto produto = produtoService.find(id);

        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value="nome", defaultValue = "")String nome,
            @RequestParam(value="categorias", defaultValue = "")String categorias,
            @RequestParam(value="page", defaultValue = "0")Integer page,
            @RequestParam (value="linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam (value="orderBy", defaultValue = "nome")String orderby,
            @RequestParam (value="direction", defaultValue = "ASC") String direction){
        List<Integer> ids = URL.decodeIntListLambda(categorias);
        String nomeDecoded = URL.decodeParam(nome);
        Page<Produto> lista = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderby, direction);
        Page<ProdutoDTO> listaDTO = lista.map(ProdutoDTO::new);
        return ResponseEntity.ok().body(listaDTO);
    }

}
