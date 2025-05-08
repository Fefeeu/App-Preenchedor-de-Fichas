package br.inatel.cdg.ficha.personagem.individualidade;

public class Classe extends Individualidade{
    private int nivel = 0;

    public Classe(String nome, String proficiencia, String[] beneficios, int nivel) {
        super(nome, proficiencia, beneficios);
        this.nivel = nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void mostrarBeneficioNivel() {
        System.out.println(beneficios[nivel]);
    }
}
