package br.inatel.cdg.ficha.personagem.individualidade;

public abstract class Individualidade {
    public String nome;
    public String proficiencia;
    public String[] beneficios = new String[20];

    public Individualidade(String nome, String proficiencia, String[] beneficios) {
        this.nome = nome;
        this.proficiencia = proficiencia;
        this.beneficios = beneficios;
    }

    public void MostarBeneficios() {
        for (int i = 0; i < beneficios.length; i++) {
            System.out.println("\nBeneficios do Level " + i + ":\n" + beneficios[i]);
        }
    }
}
