package br.rpp.usuario;

import br.rpp.ficha.Ficha;
import br.rpp.sql.BD;
import br.rpp.sql.SQLFicha;
import br.rpp.sql.Tabelas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class User {

    private final int idUser;
    private String userName;
    private String email;
    private String senha;
    private HashMap<Integer, Ficha> fichas;

    public User(int idUser, String userName, String email, String senha) {
        this.idUser = idUser;
        this.userName = userName;
        this.email = email;
        this.senha = senha;
        ArrayList<Ficha> fichas = new ArrayList<>();
    }

    public void criaFicha() {
        Ficha novaFicha = new Ficha(
                BD.gerarId(Tabelas.FICHA.toString()),
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
                "defeito"
        );
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
                "defeito"
        );
        this.fichas.remove(fichaAntiga.getIdFicha());
        this.fichas.put(fichaAntiga.getIdFicha(), novaFicha);

        SQLFicha.updateFicha(novaFicha);
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

}
