package br.rpp.ficha;

import java.util.HashMap;

public class Ficha {
    private boolean estado;
    Caracteristicas caracteristicas;
    HashMap<String, Integer> atributos;
    HashMap<String, Integer> pericias;
    int iniciativa;
    int classeArmadura; //fazer verificação
    float desloamento;
    int pontosVida;
    int vidaTemporaria;
    Dado dadoDeVida;
    Inventario inventario;
    Descricao descricao;
    TabelaMagia magias;
    private int[] proeficiencia = new int[20];
    boolean inspiracao;


    public Ficha() {
        this.atributos = new HashMap<>();
        this.pericias = new HashMap<>();
    }
}
