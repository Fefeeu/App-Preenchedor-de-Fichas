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
}
