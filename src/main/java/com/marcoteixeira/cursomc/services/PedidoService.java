package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.ItemPedido;
import com.marcoteixeira.cursomc.domain.PagamentoBoleto;
import com.marcoteixeira.cursomc.domain.Pedido;
import com.marcoteixeira.cursomc.domain.enums.EstadoPagamento;
import com.marcoteixeira.cursomc.repositories.ItemPedidoRepository;
import com.marcoteixeira.cursomc.repositories.PagamentoRepository;
import com.marcoteixeira.cursomc.repositories.PedidoRepository;
import com.marcoteixeira.cursomc.repositories.ProdutoRepository;
import com.marcoteixeira.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final BoletoService boletoService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PagamentoRepository pagamentoRepository;
    private final EmailService emailService;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         BoletoService boletoService,
                         ClienteService clienteService,
                         ProdutoService produtoService,
                         PagamentoRepository pagamentoRepository,
                         EmailService emailService,
                         ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.boletoService = boletoService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.pagamentoRepository = pagamentoRepository;
        this.emailService = emailService;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado id: " + id + ", Tipo: " + Pedido.class.getName())
        );
    }

    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if(pedido.getPagamento() instanceof PagamentoBoleto){
            PagamentoBoleto pagamentoBoleto = (PagamentoBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagamentoBoleto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for(ItemPedido ip : pedido.getItens()){
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        emailService.sendOrderConfirmationHtmlEmail(pedido);
        return pedido;
    }
}
