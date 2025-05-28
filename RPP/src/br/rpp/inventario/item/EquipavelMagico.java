package br.rpp.inventario.item;

import br.rpp.interfaces.Usavel;

public class EquipavelMagico extends Equipavel implements Usavel {
    public String efeito;
    private int cargas;
    private int cargasMaxima;
    public int bonus;

    public EquipavelMagico(String nome, String descricao, float peso, char moeda, int preco, int bonusCA, boolean proficiencia, String efeito, int cargas, int bonus){
        super(nome, descricao, peso, moeda, preco, bonusCA, proficiencia);
        this.efeito = efeito;
        this.cargas = cargas;
        this.cargasMaxima = cargas;
        this.bonus = bonus;
    }

    @Override
    public void usar(){
        // --cargas
        // exeption, se nao tiver carga
    }

    @Override
    public void recuperarUsos(){
        // cargas ++
        // exeption, se nao tiver carga
    }

    @Override
    public void recuperarQuantidadeDeUsos(int usos){
        // cargas += usos
    }
}
