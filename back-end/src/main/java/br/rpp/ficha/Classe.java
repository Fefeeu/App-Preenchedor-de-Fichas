package br.rpp.ficha;

import br.rpp.sql.SQLClasse;

public class Classe {
    private final String id;
    public String nome;
    public String descricao;

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
}
