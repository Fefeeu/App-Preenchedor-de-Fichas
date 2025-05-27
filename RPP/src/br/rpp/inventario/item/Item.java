package br.rpp.inventario.item;

public class Item {
    public String nome;
    public String descricao;
    public float peso;
    public char moeda;
    public int preco;

    public Item(String nome, String descricao, float peso, char moeda, int preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        this.moeda = moeda;
        this.preco = preco;
    }
}
