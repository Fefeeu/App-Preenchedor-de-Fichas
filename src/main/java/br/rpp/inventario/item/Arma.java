package br.rpp.inventario.item;

import br.rpp.auxiliar.Dado;
import br.rpp.ficha.Ficha;
import br.rpp.interfaces.roll20;
import br.rpp.interfaces.rollADice;

public class Arma extends Item implements roll20, rollADice {
    private Ficha ficha;
    public int dadoDeDano;
    public int quantidadeDeDados;
    public String atributo;
    public boolean proficiencia;

    public Arma(int id, Ficha ficha, String nome, String descricao, float peso, char moeda, int preco, int dado, int quantidade, String atributo, boolean proficiencia) {
        super(id, nome, descricao, peso, moeda, preco);
        this.dadoDeDano = dado;
        this.quantidadeDeDados = quantidade;
        this.atributo = atributo;
        this.proficiencia = proficiencia;

        this.ficha = ficha;
        this.setTipo("arma");
    }

    @Override
    public int rodarDadoTeste(String tipo) {
        if (proficiencia) {
            return Dado.rollD20(tipo) + ficha.atributos.get(tipo);
        }
        return Dado.rollD20(tipo);
    }

    @Override
    public int rodarDado() {
        return Dado.rollD(this.dadoDeDano, this.quantidadeDeDados);
    }

    // TODO: criar um metodo, para transformar a arma em magica
}
