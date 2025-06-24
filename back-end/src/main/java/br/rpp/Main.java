package br.rpp;

import br.rpp.auxiliar.enuns.Tabelas;
import br.rpp.ficha.Classe;
import br.rpp.ficha.Raca;
import br.rpp.magias.Magia;
import br.rpp.sql.BD;
import br.rpp.sql.SQLMagia;
import br.rpp.sql.SQLUser;
import br.rpp.usuario.User;

public class Main {
    public static void main(String[] args) {
        // TODO: ARRUMA TODOS OS EXEPTIONS, MEXER EM TODOS OS QUE TEM O throws SQLException


         User usuario = new User(BD.gerarId(Tabelas.USER.toString()), "fefe", "email@teste.com", "1234", false);
         usuario = SQLUser.readUser(1);
        System.out.println("usuario criado com sucesso!");
        usuario.criaFicha();
        System.out.println("Ficha criada com sucesso!");
        usuario.criaFicha();
        System.out.println("Ficha criada com sucesso!");

         System.out.println(usuario.getFicha(1).toString());
         System.out.println(usuario.getFicha(2).toString());
         System.out.println(usuario.getFicha(1).getMagias().getId());
         System.out.println(usuario.getFicha(1).getInventario().getId());

        usuario.getFicha(1).getMagias().criarMagia("efeito");
        Magia magia1 = SQLMagia.readMagia(1);
        System.out.println("Magia criada com sucesso! MAGIA: " + magia1.getIdMagia());

        usuario.getFicha(1).getMagias().criarMagia("dano");
        Magia magia2 = SQLMagia.readMagia(2);
        System.out.println("Magia criada com sucesso! MAGIA: " + magia2.getIdMagia());


        usuario.getFicha(1).getMagias().criarMagia("cura");
        Magia magia3 = SQLMagia.readMagia(3);
        System.out.println("Magia criada com sucesso! MAGIA: " + magia3.getIdMagia());


        System.out.println(usuario.getFicha(1).getMagias().getMagia(1).getDescricao());
        System.out.println(usuario.getFicha(1).getMagias().getMagia(3).getDescricao());
        System.out.println(usuario.getFicha(1).getMagias().getMagia(2).getDescricao());


         usuario.getFicha(1).getInventario().criarItem("comum",  usuario.getFicha(1));
        System.out.println(usuario.getFicha(1).getInventario().getItem(1));
        System.out.println("Item criada com sucesso!");

         usuario.getFicha(1).getInventario().criarItem("consumivel",  usuario.getFicha(1));
        System.out.println(usuario.getFicha(1).getInventario().getItem(2));
        System.out.println("Item criada com sucesso!");

         usuario.getFicha(1).getInventario().criarItem("magico",  usuario.getFicha(1));
        System.out.println(usuario.getFicha(1).getInventario().getItem(3));
        System.out.println("Item criada com sucesso!");

         usuario.getFicha(1).getInventario().criarItem("arma",  usuario.getFicha(1));
        System.out.println(usuario.getFicha(1).getInventario().getItem(4));
        System.out.println("Item criada com sucesso!");

         usuario.getFicha(1).getInventario().criarItem("armaMagica",  usuario.getFicha(1));
        System.out.println(usuario.getFicha(1).getInventario().getItem(5));
        System.out.println("Item criada com sucesso!");

         usuario.getFicha(1).getInventario().criarItem("equipavel",  usuario.getFicha(1));
        System.out.println(usuario.getFicha(1).getInventario().getItem(6));
        System.out.println("Item criada com sucesso!");

         usuario.getFicha(1).getInventario().criarItem("equipavelMagico",  usuario.getFicha(1));
        System.out.println(usuario.getFicha(1).getInventario().getItem(7));
        System.out.println("Item criada com sucesso!");

    }
}