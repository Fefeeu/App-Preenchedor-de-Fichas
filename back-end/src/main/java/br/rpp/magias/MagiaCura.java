package br.rpp.magias;

import br.rpp.auxiliar.enuns.TiposMagia;
import br.rpp.auxiliar.exeptions.DadoInvalidoException;
import br.rpp.auxiliar.exeptions.ValorMenorQueUmException;
import br.rpp.dado.Dado;

public class MagiaCura extends Magia {
    private int dadoCura;
    private int quantidadeDado;

    public MagiaCura(int id, String nome, String descricao, int nivel, String tempoConjuracao,
                     String duracao, String alcance, String area, String escola, String tipoAcerto,
                     int dadoCura, int quantidadeDado) {
        super(id, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto);
        this.setTipo(TiposMagia.CURA.toString());
        this.dadoCura = dadoCura;
        this.quantidadeDado = quantidadeDado;
    }

    @Override
    public int usarMagia() {
        return Dado.rollD(this.dadoCura, this.quantidadeDado);
    }

    public int getDadoCura() {
        return dadoCura;
    }

    public void setDadoCura(int dadoCura) {
        if (!Dado.verificaLados(dadoCura)) {
            throw new DadoInvalidoException();
        }

        try{
            this.dadoCura = dadoCura;
        } catch(NullPointerException e){
            System.out.println("valor do dadoCura não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getQuantidadeDado() {
        return quantidadeDado;
    }

    public void setQuantidadeDado(int quantidadeDado) {
        if (quantidadeDado < 1) {
            throw new ValorMenorQueUmException();
        }

        try{
            this.quantidadeDado = quantidadeDado;
        } catch(NullPointerException e){
            System.out.println("valor do quantidadeDado não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}
