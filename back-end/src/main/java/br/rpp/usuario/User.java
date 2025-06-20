package br.rpp.usuario;

import br.rpp.auxiliar.enuns.Tabelas;
import br.rpp.ficha.Ficha;
import br.rpp.sql.*;

import java.util.HashMap;


public class User {

    private int idUser;
    private String userName;
    private String email;
    private String senha;
    private HashMap<Integer, Ficha> fichas;

    public User(int idUser, String userName, String email, String senha, boolean read) {
        this.idUser = idUser;
        this.userName = userName;
        this.email = email;
        this.senha = senha;
        this.fichas = new HashMap<>();

        if (!read){
            SQLUser.createUser(this);
        }
    }

    public void criaFicha() {
        Ficha novaFicha = new Ficha(
                BD.gerarId(Tabelas.FICHA.toString()),
                this.idUser,
                true,
                1,
                "nome",
                "guerreiro",
                "atecedente",
                this.userName,
                "humano",
                "tendencia",
                100000,
                21,
                1.83f,
                65.5f,
                "olho",
                "pele",
                "cabelo",
                "idiomas",
                "proficiencias",
                18,
                20,
                14,
                10,
                8,
                11,
                10.5f,
                83,
                0,
                12,
                "historia",
                "aparencia",
                "personalidade",
                "ideal",
                "ligacao",
                "defeito",
                false
        );

        SQLInventario.createInventario(novaFicha.getInventario());
        SQLMagiaUser.createMagiaUser(novaFicha.getMagias());

        this.fichas.put(novaFicha.getIdFicha(), novaFicha);
        SQLFicha.createFicha(novaFicha);
    }

    public void atualizaFicha(Ficha fichaAntiga) {
        Ficha novaFicha = new Ficha(
                fichaAntiga.getIdFicha(),
                this.idUser,
                false,
                20,
                "nome",
                "idClasse",
                "atecedente",
                this.userName,
                "idRaca",
                "tendencia",
                100000,
                21,
                1.83f,
                65.5f,
                "olho",
                "pele",
                "cabelo",
                "idiomas",
                "proficiencias",
                18,
                20,
                14,
                10,
                8,
                11,
                10.5f,
                83,
                0,
                12,
                "historia",
                "aparencia",
                "personalidade",
                "ideal",
                "ligacao",
                "defeito",
                false
        );

        this.fichas.remove(fichaAntiga.getIdFicha());
        this.fichas.put(fichaAntiga.getIdFicha(), novaFicha);

        SQLFicha.updateFicha(novaFicha);
    }

    public void adicionaFicha(Ficha ficha) {
        this.fichas.put(ficha.getIdFicha(), ficha);
    }

    public void removerFicha(Ficha ficha) {
        this.fichas.remove(ficha.getIdFicha());
        SQLFicha.deleteFicha(ficha.getIdFicha());
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Ficha getFicha(int idFicha) {
        return this.fichas.get(idFicha);
    }

    public void setIdUser(int idUser) {
        try {
            this.idUser = idUser;
        } catch (NullPointerException e) {
            System.out.println("valor do idUser não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setUserName(String userName) {
        try {
            this.userName = userName;
        } catch (NullPointerException e) {
            System.out.println("valor do userName não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setEmail(String email) {
        try {
            this.email = email;
        } catch (NullPointerException e) {
            System.out.println("valor do email não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setSenha(String senha) {
        try {
            this.senha = senha;
        } catch (NullPointerException e) {
            System.out.println("valor do senha não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public HashMap<Integer, Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(HashMap<Integer, Ficha> fichas) {
        try {
            this.fichas = fichas;
        } catch (NullPointerException e) {
            System.out.println("valor do fichas não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}