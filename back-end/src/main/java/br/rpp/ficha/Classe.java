package br.rpp.ficha;

import br.rpp.sql.SQLClasse;

public class Classe {
    private final String id;
    private String nome;
    private String descricao;

    public Classe(String id, String nome, String descricao, boolean read) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        if (!read) {
            SQLClasse.createClasse(this);
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
