package br.inatel.cdg.ficha.personagem.individualidade;

public class Raca extends Individualidade{
    public int velocidadeFeats;
    public SubRaca subRaca;

    public Raca(String nome, String proficiencia, String[] beneficios, int velocidadeFeats) {
        super(nome, proficiencia, beneficios);
        this.velocidadeFeats = velocidadeFeats;
    }
    public Raca(String nome, String proficiencia, String[] beneficios, int velocidadeFeats, String subNome, String subProficiencia, String[] subBeneficios) {
        super(nome, proficiencia, beneficios);
        this.velocidadeFeats = velocidadeFeats;
        this.subRaca = new SubRaca(subNome, subProficiencia, subBeneficios);
    }
}
