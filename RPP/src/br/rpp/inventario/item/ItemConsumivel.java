package br.rpp.inventario.item;

import br.rpp.interfaces.Consumivel;

public class ItemConsumivel extends Item implements Consumivel {
    private int usos = 0;
    private int usosMaximo;

    public ItemConsumivel(String nome, String descricao, float peso, char moeda, int preco, int usos) {
        super(nome, descricao, peso, moeda, preco);
        this.usos = usos;
        usosMaximo = usos;
    }

    @Override
    public void usar(){
        // --usos
        // verificaçao se o uso for menor que 1, perguntar se quer tirar do invetario
    }

    @Override
    public void recuperarQuantidadeDeUsos(int usos) {
        // usos += usos
    }

    @Override
    public void recuperarUsos(){
        usos = usosMaximo;
    }

}
