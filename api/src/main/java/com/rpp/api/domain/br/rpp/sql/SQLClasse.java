package com.rpp.api.domain.br.rpp.sql;

import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;
import com.rpp.api.domain.br.rpp.ficha.Classe;

import java.sql.*;
import java.util.Objects;

public abstract class SQLClasse {
    public static void createClasse(Classe classe){
        Connection connection = BD.getConnection();
        String sql = "INSERT INTO " + Tabelas.CLASSE + " (" +
                "idClasse, nome, descricao" +
                ") VALUES (?, ?, ?)";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Chaves primárias (exatamente como na tabela)
            try {
                stmt.setString(index++, classe.getId());
                stmt.setString(index++, classe.getNome());
                stmt.setString(index, classe.getDescricao());

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Classe readClasse(String idClasse){
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.CLASSE + " WHERE idClasse = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setString(1, idClasse);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Classe(
                        rs.getString("idClasse"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        true
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se a classe não for encontrada
    }

    public static void deleteClasse(String idClasse){
        Connection connection = BD.getConnection();

        String sql = "DELETE FROM " + Tabelas.CLASSE + " WHERE idClasse = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setString(1, idClasse);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}