package com.marcoteixeira.cursomc.resources;

import com.marcoteixeira.cursomc.domain.Cidade;
import com.marcoteixeira.cursomc.domain.Estado;
import com.marcoteixeira.cursomc.dto.CidadeDTO;
import com.marcoteixeira.cursomc.dto.EstadoDTO;
import com.marcoteixeira.cursomc.services.CidadeService;
import com.marcoteixeira.cursomc.services.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    private final EstadoService estadoService;
    private final CidadeService cidadeService;


    public EstadoResource(EstadoService estadoService, CidadeService cidadeService) {
        this.estadoService = estadoService;
        this.cidadeService = cidadeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll(){
        List<Estado> listaEstado = estadoService.findAll();
        List<EstadoDTO> listaEstadoDTO = listaEstado.stream().map(EstadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaEstadoDTO);
    }

    @RequestMapping(value = "{estadoId}/cidades" ,method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findByCidade(@PathVariable Integer estadoId){
        List<Cidade> listaCidade = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listaCidadeDTO = listaCidade.stream().map(CidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaCidadeDTO);
    }

}
