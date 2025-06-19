package com.rpp.api.domain.br.rpp.inventario.item;

public class Equipavel extends Item {
    private int bonusCA = 0;
    private boolean proficiencia;

    public Equipavel(int id, String nome, String descricao, float peso, char moeda, int preco, int bonusCA, boolean proficiencia){
        super(id, nome, descricao, peso, moeda, preco);
        this.bonusCA = bonusCA;
        this.proficiencia = proficiencia;
        this.setTipo("equipavel");
    }

    public int getBonusCA() {
        return bonusCA;
    }

    public void setBonusCA(int bonusCA) {
        this.bonusCA = bonusCA;
    }

    public boolean getProficiencia() {
        return proficiencia;
    }

    public void setProficiencia(boolean proficiencia) {
        this.proficiencia = proficiencia;
    }


}
