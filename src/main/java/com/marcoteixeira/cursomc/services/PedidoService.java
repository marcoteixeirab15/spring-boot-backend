package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.Categoria;
import com.marcoteixeira.cursomc.domain.Pedido;
import com.marcoteixeira.cursomc.repositories.CategoriaRepository;
import com.marcoteixeira.cursomc.repositories.PedidoRepository;
import com.marcoteixeira.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscar(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado id: " + id + ", Tipo: " + Pedido.class.getName())
        );
    }

}
