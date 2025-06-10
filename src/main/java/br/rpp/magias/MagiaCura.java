package br.rpp.magias;

import br.rpp.dado.Dado;

public class MagiaCura extends Magia {
    public int dadoCura;
    public int quantidadeDado;

    public MagiaCura(int id, String tipo, String nome, String descricao, int nivel, String tempoConjuracao,
                     String duracao, String alcance, String area, String escola, String tipoAcerto,
                     int dadoCura, int quantidadeDado) {
        super(id, tipo, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto);
        this.dadoCura = dadoCura;
        this.quantidadeDado = quantidadeDado;
    }

    @Override
    public int usarMagia() {
        int cura = Dado.rollD(this.dadoCura, this.quantidadeDado);
        return cura;
    }
}
