package br.rpp.inventario.item;

import br.rpp.auxiliar.exeptions.ValorNegativoException;
import br.rpp.interfaces.ItemUsavel;

public class EquipavelMagico extends Equipavel implements ItemUsavel {
    private String efeito;
    private int cargas;
    private int cargasMaxima;
    private int bonus;

    public EquipavelMagico(int id, String nome, String descricao, float peso, char moeda, int preco, int bonusCA, boolean proficiencia, String efeito, int cargas, int bonus) {
        super(id, nome, descricao, peso, moeda, preco, bonusCA, proficiencia);
        this.efeito = efeito;
        this.cargas = cargas;
        this.cargasMaxima = cargas;
        this.bonus = bonus;
        this.setTipo("equipavelMagico");
    }

    @Override
    public void usar() {
        if (this.cargas > 0) {
            this.cargas--;
        } else {
            System.out.println("Cargas Insuficiente");
        }
    }

    @Override
    public void recuperarUsos() {
        this.cargas = this.cargasMaxima;
    }

    @Override
    public void recuperarQuantidadeDeUsos(int usos) {
        usos = Math.max(0, usos); // garante que não seja negativo
        if(usos > cargasMaxima - cargas) {
            this.cargas = this.cargasMaxima;
        } else {
            this.cargas += usos;
        }
    }

    public int getCargas() {
        return cargas;
    }

    public void setCargas(int cargas) {
        if (cargas < 0){
            throw new ValorNegativoException();
        }

        try {
            this.cargas = cargas;
        } catch (NullPointerException e) {
            System.out.println("valor do cargas não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getCargasMaxima() {
        return cargasMaxima;
    }

    public void setCargasMaxima(int cargasMaxima) {
        try {
            this.cargasMaxima = cargasMaxima;
        } catch (NullPointerException e) {
            System.out.println("valor do cargasMaxima não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        try {
            this.efeito = efeito;
        } catch (NullPointerException e) {
            System.out.println("valor do efeito não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        if (bonus < 0){
            throw new ValorNegativoException();
        }

        try {
            this.bonus = bonus;
        } catch (NullPointerException e) {
            System.out.println("valor do bonus não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}