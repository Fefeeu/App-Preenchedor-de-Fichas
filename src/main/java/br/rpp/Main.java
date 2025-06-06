package br.rpp;

import br.rpp.ficha.Ficha;
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
        String senha = "minha_senha"; // substitua pela sua senha
        int idFichaDesejada = 0;

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão estabelecida com sucesso!");

            Ficha teste = Ficha.read(conexao, idFichaDesejada);

            if (teste != null) {
                System.out.println("Ficha carregada:\n" + teste.toString());
            } else {
                System.out.println("Ficha não encontrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro na conexão ou consulta:");
            e.printStackTrace();
        }
    }
}