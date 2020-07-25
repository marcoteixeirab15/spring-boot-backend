package com.marcoteixeira.cursomc.domain.enums;

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private final int cod;
    private final String descricao;

    Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }


    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Perfil estadoPagamento : Perfil.values()) {
            if (cod.equals(estadoPagamento.getCod())) {
                return estadoPagamento;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }


}
