package br.rpp.sql;

import br.rpp.ficha.Raca;

import java.sql.*;
import java.util.Objects;

public abstract class SQLRaca {
    public static void createRaca(Raca raca){
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO " + Tabelas.RACA + " (" +
                "idRaca, nome, descricao" +
                ") VALUES (?, ?, ?)" +
                "" +
                "";

        try ( PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Chaves primárias (exatamente como na tabela)
            try {
                stmt.setString(index++, raca.getId());
                stmt.setString(index++, raca.nome);
                stmt.setString(index, raca.descricao);

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Raca readRaca(String idRaca) {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.RACA + " WHERE idRaca = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setString(1, idRaca);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Raca(
                        rs.getString("idRaca"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se a magia não for encontrada
    }

    public static void deleteRaca(String idRaca) {
        Connection connection = BD.getConnection();
        String sql = "DELETE FROM " + Tabelas.RACA + " WHERE idRaca = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            try {
                stmt.setString(1, idRaca);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}