package br.rpp.magias;

import br.rpp.auxiliar.enuns.TiposMagia;
import br.rpp.dado.Dado;

public class MagiaCura extends Magia {
    public int dadoCura;
    public int quantidadeDado;

    public MagiaCura(int id, String nome, String descricao, int nivel, String tempoConjuracao,
                     String duracao, String alcance, String area, String escola, String tipoAcerto,
                     int dadoCura, int quantidadeDado) {
        super(id, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto);
        this.tipo = TiposMagia.CURA.toString();
        this.dadoCura = dadoCura;
        this.quantidadeDado = quantidadeDado;
    }

    @Override
    public int usarMagia() {
        return Dado.rollD(this.dadoCura, this.quantidadeDado);
    }
}
