package br.rpp;

import br.rpp.magias.Magia;
import br.rpp.sql.SQLMagia;
import br.rpp.sql.SQLUser;
import br.rpp.usuario.User;

public class Main {
    public static void main(String[] args) {
        // TODO: ARRUMA TODOS OS EXEPTIONS, MEXER EM TODOS OS QUE TEM O throws SQLException

        // Classe classe = new Classe("guerreiro", "guerreiro", "um gerreiro", false);
        // Raca raca = new Raca("humano", "humano", "humano", false);


        // User usuario = new User(BD.gerarId(Tabelas.USER.toString()), "fefe", "email@teste.com", "1234");
        User usuario = SQLUser.readUser(1);
        System.out.println("usuario criado com sucesso!");
        //usuario.criaFicha();
        System.out.println("Ficha criada com sucesso!");
        //usuario.criaFicha();
        System.out.println("Ficha criada com sucesso!");

        // System.out.println(usuario.getFicha(1).toString());
        // System.out.println(usuario.getFicha(2).toString());
        // System.out.println(usuario.getFicha(1).magias.getId());
        // System.out.println(usuario.getFicha(1).inventario.getId());

        //usuario.getFicha(1).magias.criarMagia("efeito");
        Magia magia1 = SQLMagia.readMagia(1);
        System.out.println("Magia criada com sucesso! MAGIA: " + magia1.getIdMagia());

        //usuario.getFicha(1).magias.criarMagia("dano");
        Magia magia2 = SQLMagia.readMagia(2);
        System.out.println("Magia criada com sucesso! MAGIA: " + magia2.getIdMagia());


        //usuario.getFicha(1).magias.criarMagia("cura");
        Magia magia3 = SQLMagia.readMagia(3);
        System.out.println("Magia criada com sucesso! MAGIA: " + magia3.getIdMagia());


        System.out.println(usuario.getFicha(1).magias.getMagia(1).descricao);
        System.out.println(usuario.getFicha(1).magias.getMagia(3).descricao);
        System.out.println(usuario.getFicha(1).magias.getMagia(2).descricao);


        usuario.getFicha(1).inventario.criarItem("comum", 1);
        System.out.println("Item criada com sucesso!");
        usuario.getFicha(1).inventario.criarItem("consumivel", 1);
        System.out.println("Item criada com sucesso!");
        usuario.getFicha(1).inventario.criarItem("magico", 1);
        System.out.println("Item criada com sucesso!");
        usuario.getFicha(1).inventario.criarItem("arma", 1);
        System.out.println("Item criada com sucesso!");
        usuario.getFicha(1).inventario.criarItem("armaMagica", 1);
        System.out.println("Item criada com sucesso!");
        usuario.getFicha(1).inventario.criarItem("equipavel", 1);
        System.out.println("Item criada com sucesso!");
        usuario.getFicha(1).inventario.criarItem("equipavelMagico", 1);
        System.out.println("Item criada com sucesso!");
    }
}