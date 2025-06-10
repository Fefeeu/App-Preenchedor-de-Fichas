package br.rpp.sql;

import br.rpp.ficha.Raca;
import br.rpp.usuario.User;

import java.sql.*;
import java.util.Objects;

public abstract class SQLUser {
    public static void createUser(User user){
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO user (" +
                "idUser, nome, email, senha" +
                ") VALUES (?, ?, ?)";

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
        String sql = "SELECT * FROM user WHERE idItem = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idUser);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("idUser"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se o user não for encontrada
    }

    public static void deleteUser(int idUser) {
        Connection connection = BD.getConnection();
        String sql = "DELETE FROM user WHERE id = ?";
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
