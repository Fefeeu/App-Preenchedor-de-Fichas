package com.rpp.api.domain.br.rpp.sql;

import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;
import com.rpp.api.domain.br.rpp.inventario.Inventario;
import com.rpp.api.domain.br.rpp.inventario.item.Item;

import java.sql.*;
import java.util.ArrayList;
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

        Inventario novoInventario = null;

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idInventario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    novoInventario = new Inventario(
                            rs.getInt("idInventario")
                    );
                    novoInventario.setMoedas('c', rs.getInt("pc"));
                    novoInventario.setMoedas('p', rs.getInt("pp"));
                    novoInventario.setMoedas('e', rs.getInt("pe"));
                    novoInventario.setMoedas('o', rs.getInt("po"));
                    novoInventario.setMoedas('l', rs.getInt("pl"));
                }
            }

            String sqlItens = "SELECT idItem FROM " + Tabelas.ITEM + " WHERE inventario_idInventario = ?";
            ArrayList<Integer> itensIds = new ArrayList<>();

            try (PreparedStatement stmtItens = connection.prepareStatement(sqlItens)) {
                stmtItens.setInt(1, idInventario);

                try (ResultSet rsItens = stmtItens.executeQuery()) {
                    while (rsItens.next()) {
                        itensIds.add(rsItens.getInt("idItem"));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (Integer itensId : itensIds) {
                Item item = SQLItem.readItem(itensId);
                if (novoInventario != null) {
                    novoInventario.guardarItem(item, true);
                }
            }

            return novoInventario;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateInventario(Inventario inventario) {
        Connection connection = BD.getConnection();
        String sql = "UPDATE " + Tabelas.INVENTARIO + " SET " +
                "pc = ?, " +
                "pp = ?, " +
                "pe = ?, " +
                "po = ?, " +
                "pl = ? " +
                "WHERE idInventario = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            int index = 1;

            // Valores das moedas
            stmt.setInt(index++, inventario.getMoedas('c'));
            stmt.setInt(index++, inventario.getMoedas('p'));
            stmt.setInt(index++, inventario.getMoedas('e'));
            stmt.setInt(index++, inventario.getMoedas('o'));
            stmt.setInt(index++, inventario.getMoedas('l'));

            // ID do inventário (condição WHERE)
            stmt.setInt(index, inventario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar inventário: " + e.getMessage(), e);
        }
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
