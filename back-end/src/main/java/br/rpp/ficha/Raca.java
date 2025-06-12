package br.rpp.ficha;

import br.rpp.sql.SQLRaca;

public class Raca {
    private String id;
    public String nome;
    public String descricao;

    public Raca(String id, String nome, String descricao, boolean read) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;

        if (!read){
            SQLRaca.createRaca(this);
        }
    }

    public String getId() {
        return id;
    }
}
