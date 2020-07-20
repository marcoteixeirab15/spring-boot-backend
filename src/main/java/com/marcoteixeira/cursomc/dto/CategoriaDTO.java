package com.marcoteixeira.cursomc.dto;

import com.marcoteixeira.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigátorio.")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String nome;

    public CategoriaDTO(){

    }

    public CategoriaDTO(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
