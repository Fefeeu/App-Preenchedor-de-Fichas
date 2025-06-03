<<<<<<<< HEAD:RPP/src/br/rpp/ficha/Ficha.java
package br.rpp.ficha;
========
package br.rpp.personagem;
>>>>>>>> 39744c5f9f67744ed33029f4fd53acc53a80b440:RPP/src/br/rpp/personagem/Ficha.java

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
    private TabelaMagia magias;
    private int[] proeficiencia = new int[20];
    boolean inspiracao;


    public Ficha() {
        this.atributos = new HashMap<>();
        this.pericias = new HashMap<>();
    }


}
