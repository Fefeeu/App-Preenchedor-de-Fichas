package br.rpp.inventario.item;

public class Equipavel extends Item {
    public int bonusCA = 0;
    public boolean proficiencia;

    public Equipavel(String nome, String descricao, float peso, char moeda, int preco, int bonusCA, boolean proficiencia){
        super(nome, descricao, peso, moeda, preco);
        this.bonusCA = bonusCA;
        this.proficiencia = proficiencia;
    }
}
