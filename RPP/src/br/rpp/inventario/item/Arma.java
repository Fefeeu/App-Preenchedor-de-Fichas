package br.rpp.inventario.item;

import br.rpp.interfaces.roll20;
import br.rpp.interfaces.rollADice;

public class Arma extends Item implements roll20, rollADice {
    public Dado dadoDeDano;
    public int quantidadeDeDados;
    public int atributo;
    public boolean proficiencia;

    public Arma(String nome, String descricao, float peso, char moeda, int preco, Dado dado, int quantidade, int atributo, boolean proficiencia) {
        super(nome, descricao, peso, moeda, preco);
        this.dadoDeDano = dado;
        this.quantidadeDeDados = quantidade;
        this.atributo = atributo;
        this.proficiencia = proficiencia;
    }

    @Override
    public int rodarDadoTeste(String vantagem) {
        // return Dado.rodarD(int) + bonus
        return 0;
    }

    @Override
    public int rodarDado() {
        // return Dado.rodarD20(vantagens, desvantagem, normal) + bonus
        return 0;
    }

    public
}
