package br.rpp.ficha;

public class Raca {
    private String id;
    public String nome;
    public String descricao;

    public Raca(String id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }
}
