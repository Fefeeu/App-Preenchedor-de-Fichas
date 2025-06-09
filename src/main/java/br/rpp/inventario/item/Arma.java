package br.rpp.inventario.item;

import br.rpp.dado.Dado;
import br.rpp.ficha.Ficha;
import br.rpp.interfaces.Roll20;
import br.rpp.interfaces.RollADice;

public class Arma extends Item implements Roll20, RollADice {
    public Ficha ficha;
    public int dadoDeDano;
    public int quantidadeDeDados;
    public String atributo;
    public boolean proficiencia;

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
        int valor = Dado.rollD20(tipo) + this.ficha.atributos.get(atributo);
        if (proficiencia) {
            return valor + Ficha.getProficiencia(this.ficha.nivel);
        }
        return valor;
    }

    @Override
    public int rodarDado() {
        return Dado.rollD(this.dadoDeDano, this.quantidadeDeDados) + this.ficha.atributos.get(atributo);
    }

    // TODO: criar um metodo, para transformar a arma em magica
}
