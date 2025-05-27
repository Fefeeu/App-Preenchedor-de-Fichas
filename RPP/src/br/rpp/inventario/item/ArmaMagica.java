package br.rpp.inventario.item;

import br.rpp.interfaces.Consumivel;

public class ArmaMagica extends Arma implements Consumivel {
    public String efeito;
    private int cargas;
    private int cargasMaxima;
    public int bonus;

    public ArmaMagica(String nome, String descricao, float peso, char moeda, int preco, Dado dado, int quantidade, int atributo, boolean proficiencia, String efeito, int cargas, int bonus) {
        super(nome, descricao, peso, moeda, preco, dado, quantidade, atributo, proficiencia);
        this.efeito = efeito;
        this.cargas = cargas;
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
