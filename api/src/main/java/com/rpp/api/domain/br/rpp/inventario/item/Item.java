package com.rpp.api.domain.br.rpp.inventario.item;

import com.rpp.api.domain.br.rpp.auxiliar.exeptions.TipoMoedaException;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorNegativoException;
import com.rpp.api.domain.br.rpp.magias.Magia;

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
        try {
            this.tipo = tipo;
        } catch (NullPointerException e) {
            System.out.println("valor do tipo não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        try {
            this.nome = nome;
        } catch (NullPointerException e) {
            System.out.println("valor do nome não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        try {
            this.descricao = descricao;
        } catch (NullPointerException e) {
            System.out.println("valor do descricao não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        if(peso < 0){
            throw new ValorNegativoException();
        }

        try {
            this.peso = peso;
        } catch (NullPointerException e) {
            System.out.println("valor do peso não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public char getMoeda() {
        return moeda;
    }

    public void setMoeda(char moeda) {
        if (moeda != 'c' && moeda != 'p' && moeda != 'e' && moeda != 'o' && moeda != 'l') {
            throw new TipoMoedaException();
        }

        try {
            this.moeda = moeda;
        } catch (NullPointerException e) {
            System.out.println("valor do moeda não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        if (preco < 0){
            throw new ValorNegativoException();
        }

        try {
            this.preco = preco;
        } catch (NullPointerException e) {
            System.out.println("valor do preco não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}