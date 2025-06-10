package br.rpp.ficha;

public class Classe {
    private String id;
    public String nome;
    public String descricao;

    public Classe(String id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }
}
