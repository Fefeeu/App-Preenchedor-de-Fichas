package br.rpp.ficha;

import java.util.ArrayList;

public class Caracteristica {
    String nomePersonagem;
    Classe classe;
    String antecedente;
    private String userName;
    Raca raca;
    String tendencia;
    int xp; //apenas valores positivos
    int idade; //apenas valores positivos
    float altura;
    float peso;
    String olho;
    String pele;
    String cabelo;
    ArrayList<String> idiomas;
    ArrayList<String> proeficiencia;

    public Caracteristica(String nomePersonagem, Classe classe, String antecedente,
                          String userName, Raca raca, String tendencia,
                          int xp, int idade, float altura, float peso,
                          String olho, String pele, String cabelo,
                          ArrayList<String> idiomas, ArrayList<String> proeficiencia)
    {
        this.nomePersonagem = nomePersonagem;
        this.classe = classe;
        this.antecedente = antecedente;
        this.userName = userName;
        this.raca = raca;
        this.tendencia = tendencia;
        this.xp = Math.max(0,xp);
        this.idade = Math.max(0,idade);
        this.altura = altura;
        this.peso = peso;
        this.olho = olho;
        this.pele = pele;
        this.cabelo = cabelo;
        this.idiomas = new ArrayList<>();
        this.proeficiencia = new ArrayList<>();


    }
}
