package br.rpp.sql;

import java.sql.*;

public abstract class BD {
    static private final String url = "jdbc:mysql://localhost:3306/rpp?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static private String usuario = "visualizacao";
    static private String senha = "rpp1234";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public static int gerarId(String tabela) {
        String sql = "SELECT COALESCE(MAX(id" + tabela + "), 0) + 1 FROM " + tabela;
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            if (connection == null) {
                throw new SQLException("Não foi possível estabelecer conexão com o banco de dados");
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                throw new SQLException("Nenhum resultado encontrado ao gerar ID para a tabela " + tabela);
            }
            return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Erro ao gerar ID para a tabela " + tabela + ": " + e.getMessage());
            return -1; // Valor inválido para indicar erro
        } finally {
            // Fechar recursos na ordem inversa de criação
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignorar */ }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* Ignorar */ }
            try { if (connection != null) connection.close(); } catch (SQLException e) { /* Ignorar */ }
        }
    }
}