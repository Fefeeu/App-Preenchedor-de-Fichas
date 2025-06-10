package br.rpp.ficha;

import java.util.ArrayList;

public class Caracteristica {
    public String nomePersonagem;
    //public Classe classe;
    public String idClasse;
    public String antecedente;
    public String userName;
    // public Raca raca;
    public String idRaca;
    public String tendencia;
    public int xp; //apenas valores positivos
    public int idade; //apenas valores positivos
    public float altura;
    public float peso;
    public String olho;
    public String pele;
    public String cabelo;
    public String idiomas;
    public String proeficiencias;

    public Caracteristica(String nomePersonagem, String idClasse, String antecedente,
                          String userName, String idRaca, String tendencia,
                          int xp, int idade, float altura, float peso,
                          String olho, String pele, String cabelo,
                          String idiomas, String proeficiencias)
    {
        this.nomePersonagem = nomePersonagem;
        this.idClasse = idClasse;
        this.antecedente = antecedente;
        this.userName = userName;
        this.idRaca = idRaca;
        this.tendencia = tendencia;
        this.xp = Math.max(0,xp);
        this.idade = Math.max(0,idade);
        this.altura = altura;
        this.peso = peso;
        this.olho = olho;
        this.pele = pele;
        this.cabelo = cabelo;
        this.idiomas = idiomas;
        this.proeficiencias = proeficiencias;
    }

    public String getUserName() {
        return userName;
    }
}
