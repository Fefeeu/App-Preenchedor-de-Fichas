package br.rpp.magias;

public class MagiaCura extends Magia {
    int cura;

    public MagiaCura(String nome, String descricao, int nivel, int cura) {
        super(nome, descricao, nivel, "1 ação", "Instantânea", 1.5f, "1 criatura", "Divina", 'C');
        this.cura = cura;
    }

    @Override
    public void usarMagia() {
        super.usarMagia();
        System.out.println(cura + "pontos de vida restaurados!!");
    }
}
