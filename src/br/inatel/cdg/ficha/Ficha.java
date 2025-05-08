package br.inatel.cdg.ficha;

import br.inatel.cdg.ficha.personagem.*;
import br.inatel.cdg.ficha.personagem.individualidade.Classe;
import br.inatel.cdg.ficha.personagem.individualidade.Raca;
import br.inatel.cdg.ficha.personagem.status.Pericia;
import br.inatel.cdg.ficha.personagem.status.Resistencia;
import br.inatel.cdg.ficha.personagem.status.StatusPadrao;

import java.util.ArrayList;

public class Ficha {
    public String nome;
    private int nivel;
    public int vida;
    public int classDeArmadura;
    public String alinhamento;
    public Classe classe;
    public Raca raca;
    public StatusPadrao[] status = new StatusPadrao[6];
    public Resistencia[] resistencias = new Resistencia[6];
    public Pericia[] pericias = new Pericia[18];
    private ArrayList<Inventario> inventario = new ArrayList<Inventario>();

    public void setNivel(int nivel) {
        this.nivel = nivel;
        this.classe.setNivel(nivel);
    }
}
