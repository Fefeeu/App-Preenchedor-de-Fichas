package com.rpp.api.domain.br.rpp.inventario.item;

import com.rpp.api.domain.br.rpp.dado.Dado;
import com.rpp.api.domain.br.rpp.ficha.Ficha;
import com.rpp.api.domain.br.rpp.interfaces.Roll20;
import com.rpp.api.domain.br.rpp.interfaces.RollADice;

public class Arma extends Item implements Roll20, RollADice {
    private Ficha ficha;
    private int dadoDeDano;
    private int quantidadeDeDados;
    private String atributo;
    private boolean proficiencia;

    public Arma(int id, Ficha ficha, String nome, String descricao, float peso, char moeda, int preco, int dado, int quantidade, String atributo, boolean proficiencia) {
        super(id, nome, descricao, peso, moeda, preco);
        this.ficha = ficha;
        this.dadoDeDano = dado;
        this.quantidadeDeDados = quantidade;
        this.atributo = atributo;
        this.proficiencia = proficiencia;

        this.setTipo("arma");
    }

    @Override
    public int rodarDadoTeste(String tipo) {
        int valor = Dado.rollD20(tipo) + this.ficha.converteAtributo(atributo);
        if (proficiencia) {
            return valor + Ficha.getProficiencia(this.ficha.getNivel());
        }
        return valor;
    }

    @Override
    public int rodarDado() {
        return Dado.rollD(this.dadoDeDano, this.quantidadeDeDados) + this.ficha.converteAtributo(this.atributo);
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public int getDadoDeDano() {
        return dadoDeDano;
    }

    public void setDadoDeDano(int dadoDeDano) {
        this.dadoDeDano = dadoDeDano;
    }

    public int getQuantidadeDeDados() {
        return quantidadeDeDados;
    }

    public void setQuantidadeDeDados(int quantidadeDeDados) {
        this.quantidadeDeDados = quantidadeDeDados;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public boolean isProficiencia() {
        return proficiencia;
    }

    public void setProficiencia(boolean proficiencia) {
        this.proficiencia = proficiencia;
    }

    // TODO: criar um metodo, para transformar a arma em magica
}
