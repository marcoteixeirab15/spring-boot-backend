package com.marcoteixeira.cursomc;

import com.marcoteixeira.cursomc.domain.Categoria;
import com.marcoteixeira.cursomc.domain.Cidade;
import com.marcoteixeira.cursomc.domain.Cliente;
import com.marcoteixeira.cursomc.domain.Endereco;
import com.marcoteixeira.cursomc.domain.Estado;
import com.marcoteixeira.cursomc.domain.Pagamento;
import com.marcoteixeira.cursomc.domain.PagamentoBoleto;
import com.marcoteixeira.cursomc.domain.PagamentoComCartao;
import com.marcoteixeira.cursomc.domain.Pedido;
import com.marcoteixeira.cursomc.domain.Produto;
import com.marcoteixeira.cursomc.domain.enums.EstadoPagamento;
import com.marcoteixeira.cursomc.domain.enums.TipoCliente;
import com.marcoteixeira.cursomc.repositories.CategoriaRepository;
import com.marcoteixeira.cursomc.repositories.CidadeRepository;
import com.marcoteixeira.cursomc.repositories.ClienteRepository;
import com.marcoteixeira.cursomc.repositories.EnderecoRepository;
import com.marcoteixeira.cursomc.repositories.EstadoRepository;
import com.marcoteixeira.cursomc.repositories.PagamentoRepository;
import com.marcoteixeira.cursomc.repositories.PedidoRepository;
import com.marcoteixeira.cursomc.repositories.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;

    public CursomcApplication(CategoriaRepository categoriaRepository,
                              ProdutoRepository produtoRepository,
                              EstadoRepository estadoRepository,
                              CidadeRepository cidadeRepository, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");

        Produto produto1 = new Produto(null, "Computador", 2000.00);
        Produto produto2 = new Produto(null, "Impressora", 800.00);
        Produto produto3 = new Produto(null, "Mouse", 80.00);

        categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
        categoria2.getProdutos().addAll(Collections.singletonList(produto2));

        produto1.getCategorias().addAll(Collections.singletonList(categoria1));
        produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        produto3.getCategorias().addAll(Collections.singletonList(categoria1));

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

        Estado estado1 = new Estado(null, "Minas Gerais");
        Estado estado2 = new Estado(null, "São Paulo");

        Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
        Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
        Cidade cidade3 = new Cidade(null, "Campinas", estado2);

        estado1.getCidades().addAll(Collections.singletonList(cidade1));
        estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

        estadoRepository.saveAll(Arrays.asList(estado1, estado2));
        cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

        Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);

        cliente1.getTelefone().addAll(Arrays.asList("33937720", "982523034"));

        Endereco endereco1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "72503509", cliente1, cidade1);
        Endereco endereco2 = new Endereco(null, "Avenida Martins", "105", "Sala 800", "Centro", "72503512", cliente1, cidade2);

        cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

        clienteRepository.saveAll(Collections.singletonList(cliente1));
        enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
        Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente1, endereco2);

        Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
        pedido1.setPagamento(pagamento1);

        Pagamento pagamento2 = new PagamentoBoleto(null,  EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2017 00:00"),  null);
        pedido2.setPagamento(pagamento2);

        cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
        pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));


    }
}
