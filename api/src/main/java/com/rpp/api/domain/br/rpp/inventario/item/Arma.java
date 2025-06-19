package com.rpp.api.domain.br.rpp.inventario.item;

import com.rpp.api.domain.br.rpp.auxiliar.exeptions.DadoInvalidoException;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorMenorQueUmException;
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
        try {
            this.ficha = ficha;
        } catch (NullPointerException e) {
            System.out.println("valor do ficha não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getDadoDeDano() {
        return dadoDeDano;
    }

    public void setDadoDeDano(int dadoDeDano) {
        if(!Dado.verificaLados(dadoDeDano)) {
            throw new DadoInvalidoException();
        }

        try {
            this.dadoDeDano = dadoDeDano;
        } catch (NullPointerException e) {
            System.out.println("valor do dadoDeDano não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getQuantidadeDeDados() {
        return quantidadeDeDados;
    }

    public void setQuantidadeDeDados(int quantidadeDeDados) {
        if (quantidadeDeDados < 1) {
            throw new ValorMenorQueUmException();
        }

        try {
            this.quantidadeDeDados = quantidadeDeDados;
        } catch (NullPointerException e) {
            System.out.println("valor do quantidadeDeDados não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        try {
            this.atributo = atributo;
        } catch (NullPointerException e) {
            System.out.println("valor do atributo não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public boolean isProficiencia() {
        return proficiencia;
    }

    public void setProficiencia(boolean proficiencia) {
        try {
            this.proficiencia = proficiencia;
        } catch (NullPointerException e) {
            System.out.println("valor do proficiencia não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    // TODO: criar um metodo, para transformar a arma em magica
}