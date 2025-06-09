package br.rpp.inventario.item;

import br.rpp.magias.Magia;

public class Item {
    private String tipo;
    private final int id;
    public String nome;
    public String descricao;
    public float peso;
    public char moeda;
    public int preco;

    public Item(int id, String nome, String descricao, float peso, char moeda, int preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        this.moeda = moeda;
        this.preco = preco;
        this.tipo = "comum";
    }

    public int getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
