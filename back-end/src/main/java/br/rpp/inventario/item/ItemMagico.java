package br.rpp.inventario.item;

import br.rpp.interfaces.ItemUsavel;

public class ItemMagico extends Item implements ItemUsavel {
    private String efeito;
    private int cargas;
    private int cargasMaxima;

    public ItemMagico(int id, String nome, String descricao, float peso, char moeda, int preco, String efeito, int cargas) {
        super(id, nome, descricao, peso, moeda, preco);
        this.efeito = efeito;
        this.cargas = cargas;
        this.cargasMaxima = cargas;
        this.setTipo("magico");
    }

    @Override
    public void usar(){
        if (this.cargas > 0){
            this.cargas--;
        } else {
            System.out.println("Cargas Insuficiente");
        }
    }

    @Override
    public void recuperarUsos(){
        this.cargas = this.cargasMaxima;
    }

    @Override
    public void recuperarQuantidadeDeUsos(int usos){
        usos = Math.max(0, usos); // garante que não seja negativo
        if(usos > cargasMaxima - cargas){
            this.cargas = this.cargasMaxima;
        } else {
            this.cargas+= usos;
        }
    }

    public int getCargasMaxima() {
        return cargasMaxima;
    }

    public int getCargas() {
        return cargas;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }
}
