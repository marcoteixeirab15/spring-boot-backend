package com.marcoteixeira.cursomc.repositories;

import com.marcoteixeira.cursomc.domain.Categoria;
import com.marcoteixeira.cursomc.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {



}
