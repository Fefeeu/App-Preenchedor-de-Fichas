package br.rpp.usuario;

import br.rpp.ficha.Ficha;

import java.util.ArrayList;

public class User {
    private String userName;
    private String email;
    private String senha;
    private ArrayList<Ficha> fichas;

    public User(String userName, String email, String senha) {}

    public void criaFicha() {}

    public void removerFicha() {}

    public void logout(){}
}
