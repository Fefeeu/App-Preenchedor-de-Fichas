package br.rpp.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BD {
    static String url = "jdbc:mysql://localhost:3306/rpp?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    static String usuario = "visualizacao"; // substitua pelo seu usuário
    static String senha = "rpp1234"; // substitua pela sua senha

    public static Connection getConnection() throws SQLException {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            return conexao;
        } catch (SQLException e) {
            System.err.println("Erro na conexão com o banco de dados");
            e.printStackTrace();
        }
        return null;
    }
}
