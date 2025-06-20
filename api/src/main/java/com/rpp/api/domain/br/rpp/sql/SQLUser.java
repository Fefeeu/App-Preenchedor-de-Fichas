package com.rpp.api.domain.br.rpp.sql;

import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;
import com.rpp.api.domain.br.rpp.ficha.Ficha;
import com.rpp.api.domain.br.rpp.usuario.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public abstract class SQLUser {
    public static void createUser(User user){
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO " + Tabelas.USER + " (" +
                "idUser, nome, email, senha" +
                ") VALUES (?, ?, ?, ?)";

        try ( PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Chaves primárias (exatamente como na tabela)
            try {
                stmt.setInt(index++, user.getIdUser());
                stmt.setString(index++, user.getUserName());
                stmt.setString(index++, user.getEmail());
                stmt.setString(index, user.getSenha());

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User readUser(int idUser) {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.USER + " WHERE idUser = ?";

        User user = null;

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idUser);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user =  new User(
                            rs.getInt("idUser"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            true
                    );
                }
            }

            String sqlFichas = "SELECT idFicha FROM " + Tabelas.FICHA + " WHERE user_idUser = ?";
            ArrayList<Integer> fichasIds = new ArrayList<>();

            try (PreparedStatement stmtFichas = connection.prepareStatement(sqlFichas)) {
                stmtFichas.setInt(1, idUser);

                try (ResultSet rsFichas = stmtFichas.executeQuery()) {
                    while (rsFichas.next()) {
                        fichasIds.add(rsFichas.getInt("idFicha"));
                    }
                }
            }

            for (Integer fichasId : fichasIds) {
                Ficha ficha = SQLFicha.readFicha(fichasId);
                if (user != null) {
                    user.adicionaFicha(ficha);
                }
            }
            return user;
        } catch (SQLException e) {
            System.err.println("Erro ao ler usuário ID " + idUser + ": " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return null;
    }

    public static void deleteUser(int idUser) {
        Connection connection = BD.getConnection();
        String sql = "DELETE FROM " + Tabelas.USER + " WHERE idUser = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            try {
                stmt.setInt(1, idUser);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
