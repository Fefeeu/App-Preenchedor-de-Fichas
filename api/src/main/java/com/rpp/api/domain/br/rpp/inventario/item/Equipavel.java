package com.rpp.api.domain.br.rpp.inventario.item;

import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorNegativoException;

public class Equipavel extends Item {
    private int bonusCA = 0;
    private boolean proficiencia;

    public Equipavel(int id, String nome, String descricao, float peso, char moeda, int preco, int bonusCA, boolean proficiencia) {
        super(id, nome, descricao, peso, moeda, preco);
        this.bonusCA = bonusCA;
        this.proficiencia = proficiencia;
        this.setTipo("equipavel");
    }

    public int getBonusCA() {
        return bonusCA;
    }

    public void setBonusCA(int bonusCA) {
        if (bonusCA < 0){
            throw new ValorNegativoException();
        }

        try {
            this.bonusCA = bonusCA;
        } catch (NullPointerException e) {
            System.out.println("valor do bonusCA não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public boolean getProficiencia() {
        return proficiencia;
    }

    public void setProficiencia(boolean proficiencia) {
        try {
            this.proficiencia = proficiencia;
        } catch (NullPointerException e) {
            System.out.println("valor do proficiencia não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}