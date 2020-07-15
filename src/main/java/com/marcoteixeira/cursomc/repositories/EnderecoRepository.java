package com.marcoteixeira.cursomc.repositories;

import com.marcoteixeira.cursomc.domain.Cidade;
import com.marcoteixeira.cursomc.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {



}
