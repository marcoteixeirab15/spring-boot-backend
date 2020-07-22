package com.marcoteixeira.cursomc;

import com.marcoteixeira.cursomc.domain.Categoria;
import com.marcoteixeira.cursomc.domain.Cidade;
import com.marcoteixeira.cursomc.domain.Cliente;
import com.marcoteixeira.cursomc.domain.Endereco;
import com.marcoteixeira.cursomc.domain.Estado;
import com.marcoteixeira.cursomc.domain.ItemPedido;
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
import com.marcoteixeira.cursomc.repositories.ItemPedidoRepository;
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

    @Override
    public void run(String... args) throws Exception {



    }
}
