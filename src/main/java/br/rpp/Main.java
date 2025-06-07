package br.rpp;

import br.rpp.ficha.Ficha;
import br.rpp.inventario.Inventario;
import br.rpp.inventario.item.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // 1. Registrar o driver explicitamente (opcional para versões recentes)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado!");
            e.printStackTrace();
            return;
        }

        // 2. Configurações de conexão
        String url = "jdbc:mysql://localhost:3306/rpp?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String usuario = "root"; // substitua pelo seu usuário
        String senha = "Bleach301204!"; // substitua pela sua senha
        int idFichaDesejada = 1;

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão estabelecida com sucesso!");

            Ficha teste = Ficha.read(conexao, 0);

            //Ficha.create(conexao, teste, idFichaDesejada);

            //Ficha teste2 = Ficha.read(conexao, idFichaDesejada);

            if (teste != null) {
                System.out.println("Ficha carregada:\n" + teste.toString());
            } else {
                System.out.println("Ficha não encontrada!");
            }

            //Arma arma1 = new Arma(0, teste, "arma de teste", "uma arma para testes", 3, 'o', 100, 10, 2, "forca", true);
            //Inventario.createItem(conexao, arma1, 0, 0);
            // Item Comum (básico)


        } catch (SQLException e) {
            System.err.println("Erro na conexão ou consulta:");
            e.printStackTrace();
        }
    }
}