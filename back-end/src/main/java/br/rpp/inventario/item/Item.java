package br.rpp.inventario.item;

import br.rpp.magias.Magia;

public class Item {
    private String tipo;
    private final int id;
    private String nome;
    private String descricao;
    private float peso;
    private char moeda;
    private int preco;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public char getMoeda() {
        return moeda;
    }

    public void setMoeda(char moeda) {
        this.moeda = moeda;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
