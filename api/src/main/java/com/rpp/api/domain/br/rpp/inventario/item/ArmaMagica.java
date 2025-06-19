package com.rpp.api.domain.br.rpp.inventario.item;

import com.rpp.api.domain.br.rpp.dado.Dado;
import com.rpp.api.domain.br.rpp.ficha.Ficha;
import com.rpp.api.domain.br.rpp.interfaces.Roll20;
import com.rpp.api.domain.br.rpp.interfaces.RollADice;
import com.rpp.api.domain.br.rpp.interfaces.ItemUsavel;

public class ArmaMagica extends Arma implements ItemUsavel, Roll20, RollADice {
    private String efeito;
    private int cargas;
    private int cargasMaxima;
    private int bonus;

    public ArmaMagica(int id, Ficha ficha, String nome, String descricao, float peso, char moeda, int preco, int dado, int quantidade, String atributo, boolean proficiencia, String efeito, int cargas, int bonus) {
        super(id, ficha, nome, descricao, peso, moeda, preco, dado, quantidade, atributo, proficiencia);
        this.efeito = efeito;
        this.cargas = cargas;
        this.cargasMaxima = cargas;
        this.bonus = bonus;

        this.setTipo("armaMagica");
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

    @Override
    public int rodarDadoTeste(String tipo) {
        return super.rodarDadoTeste(tipo) + this.bonus;
    }

    @Override
    public int rodarDado() {
        return super.rodarDado() + this.bonus;
    }

    public int getCargasMaxima() {
        return cargasMaxima;
    }

    public int getCargas(){
        return cargas;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
