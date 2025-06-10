package br.rpp.sql;

import java.sql.*;

public abstract class BD {
    static String url = "jdbc:mysql://localhost:3306/rpp?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    static String usuario = "visualizacao";
    static String senha = "rpp1234";

    public static Connection getConnection(){
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            return conexao;
        } catch (SQLException e) {
            System.err.println("Erro na conexão com o banco de dados");
            e.printStackTrace();
        }
        return null;
    }

    public static int gerarId(String tabela) {
        String sql = "SELECT COALESCE(MAX(idItem), 0) + 1 FROM " + tabela;

        try (Connection connection = BD.getConnection()) {
            assert connection != null;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                if (!rs.next()) {
                    throw new SQLException("Nenhum resultado encontrado ao gerar ID para item");
                }
                return rs.getInt(1);
            }catch (SQLException e) {
                System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
                throw new SQLException("Falha ao executar consulta para gerar ID", e);
            }

        } catch (SQLException e) {
            System.err.println("Erro de conexão com o banco de dados: " + e.getMessage());
            try {
                throw new SQLException("Falha na conexão ao tentar gerar ID para item", e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
