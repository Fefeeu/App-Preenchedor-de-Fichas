package br.rpp.sql;

import br.rpp.auxiliar.enuns.Tabelas;
import br.rpp.inventario.Inventario;

import java.sql.*;
import java.util.Objects;

public abstract class SQLInventario {
    public static void createInventario(Inventario inventario){
        Connection connection = BD.getConnection();
        String sql = "INSERT INTO " + Tabelas.INVENTARIO + "(" +
                "idInventario, pc, pp, pe, po, pl" +
                ") VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Chaves primárias (exatamente como na tabela)
            try {
                stmt.setInt(index++, inventario.getId());
                stmt.setInt(index++, inventario.getMoedas('c'));
                stmt.setInt(index++, inventario.getMoedas('p'));
                stmt.setInt(index++, inventario.getMoedas('e'));
                stmt.setInt(index++, inventario.getMoedas('o'));
                stmt.setInt(index, inventario.getMoedas('l'));

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Inventario readInventario(int idInventario){
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.INVENTARIO + " WHERE idInventario = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idInventario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Inventario novoInventario = new Inventario(
                            rs.getInt("idInventario")
                    );
                    novoInventario.setMoedas('c', rs.getInt("pc"));
                    novoInventario.setMoedas('p', rs.getInt("pp"));
                    novoInventario.setMoedas('e', rs.getInt("pe"));
                    novoInventario.setMoedas('o', rs.getInt("po"));
                    novoInventario.setMoedas('l', rs.getInt("pl"));

                    return novoInventario;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se a classe não for encontrada
    }

    public static void deleteInventario(int idInventario){
        Connection connection = BD.getConnection();

        String sql = "DELETE FROM " + Tabelas.INVENTARIO + " WHERE idInventario = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idInventario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
