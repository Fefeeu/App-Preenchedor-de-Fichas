package com.rpp.api.domain.br.rpp.ficha;

import com.rpp.api.domain.br.rpp.sql.SQLRaca;

public class Raca {
    private String id;
    private String nome;
    private String descricao;

    public Raca(String id, String nome, String descricao, boolean read) {
        this.id = id.toLowerCase();
        this.nome = nome;
        this.descricao = descricao;

        if (!read){
            SQLRaca.createRaca(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
