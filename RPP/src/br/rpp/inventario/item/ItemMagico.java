package br.rpp.inventario.item;

import br.rpp.interfaces.Consumivel;

public class ItemMagico extends Item implements Consumivel {
    public String efeito;
    private int cargas;
    private int cargasMaxima;

    public ItemMagico(String nome, String descricao, float peso, char moeda, int preco, String efeito) {
        super(nome, descricao, peso, moeda, preco);
        this.efeito = efeito;
    }

    public ItemMagico(String nome, String descricao, float peso, char moeda, int preco, String efeito, int cargas) {
        super(nome, descricao, peso, moeda, preco);
        this.efeito = efeito;
        this.cargas = cargas;
        this.cargasMaxima = cargas;
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
