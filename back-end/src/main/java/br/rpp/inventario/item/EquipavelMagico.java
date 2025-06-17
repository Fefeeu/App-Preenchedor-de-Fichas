package br.rpp.inventario.item;

import br.rpp.interfaces.ItemUsavel;

public class EquipavelMagico extends Equipavel implements ItemUsavel {
    private String efeito;
    private int cargas;
    private int cargasMaxima;
    private int bonus;

    public EquipavelMagico(int id, String nome, String descricao, float peso, char moeda, int preco, int bonusCA, boolean proficiencia, String efeito, int cargas, int bonus){
        super(id, nome, descricao, peso, moeda, preco, bonusCA, proficiencia);
        this.efeito = efeito;
        this.cargas = cargas;
        this.cargasMaxima = cargas;
        this.bonus = bonus;
        this.setTipo("equipavelMagico");
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

    public int getCargas() {
        return cargas;
    }

    public int getCargasMaxima() {
        return cargasMaxima;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public void setCargas(int cargas) {
        this.cargas = cargas;
    }

    public void setCargasMaxima(int cargasMaxima) {
        this.cargasMaxima = cargasMaxima;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
