package br.rpp.inventario.item;

public class Equipavel extends Item {
    public int bonusCA = 0;
    public boolean proficiencia;

    public Equipavel(int id, String nome, String descricao, float peso, char moeda, int preco, int bonusCA, boolean proficiencia){
        super(id, nome, descricao, peso, moeda, preco);
        this.bonusCA = bonusCA;
        this.proficiencia = proficiencia;
        this.setTipo("equipavel");
    }
}
