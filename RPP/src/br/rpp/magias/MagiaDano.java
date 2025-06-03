package br.rpp.magias;

public class MagiaDano extends Magia {
    int dano;

    public MagiaDano(String nome, String descricao, int nivel, int dano) {
        super(nome, descricao, nivel, "1 ação", "Instantânea", 18f, "6m raio", "Evocação", 'A');
        this.dano = dano;
    }
    @Override
    public void usarMagia() {
        super.usarMagia();
        System.out.println("Dano: " + dano);
    }
}
