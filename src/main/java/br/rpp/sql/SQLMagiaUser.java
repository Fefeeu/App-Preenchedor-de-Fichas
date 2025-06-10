package br.rpp.sql;

import br.rpp.ficha.TabelaMagia;

import java.sql.*;
import java.util.Objects;

public abstract class SQLMagiaUser{
    public static void createMagiaUser(TabelaMagia tabelaMagia){
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO " + Tabelas.MAGIAUSER + " (" +
                "idMagiaUser, nivel1, nivel2, nivel3, nivel4, nivel5, nivel6, nivel7, nivel8, nivel9" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            try {
                stmt.setInt(index++, tabelaMagia.getId());
                stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(1));
                stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(2));
                stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(3));
                stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(4));
                stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(5));
                stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(6));
                stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(7));
                stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(8));
                stmt.setInt(index, tabelaMagia.getEspacoDeMagia(9));

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TabelaMagia readMagiaUser(int idTabelaMagia, int idFicha) {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.MAGIAUSER + " WHERE idMagiaUser = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idTabelaMagia);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TabelaMagia leituraTabela = new TabelaMagia(
                            rs.getInt("idMagiaUser"),
                            SQLFicha.readFicha(idFicha)
                    );
                    // for para setar todos os espaços de nivel de magia
                    for(int i = 1; i<=9; i++){
                        leituraTabela.setEspacoDeMagia(i, rs.getInt("nivel" + i));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se a tabelaMatgia não for encontrada
    }

    public static void deleteMagiaUser(int idTabelaMagia) {
        Connection connection = BD.getConnection();
        String sql = "DELETE FROM " + Tabelas.MAGIAUSER + " WHERE idMagiaUser = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            try {
                stmt.setInt(1, idTabelaMagia);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateEspacoMagia(TabelaMagia tabelaMagia) {
        Connection connection = BD.getConnection();
        String sql = "UPDATE " + Tabelas.MAGIAUSER + " SET " +
                "nivel1 = ?, " +
                "nivel2 = ?, " +
                "nivel3 = ?, " +
                "nivel4 = ?, " +
                "nivel5 = ?, " +
                "nivel6 = ?, " +
                "nivel7 = ?, " +
                "nivel8 = ?, " +
                "nivel9 = ?, " +
                "WHERE idFicha = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 2;

            try {
                // le todos os espacos de magia
                for(int i = 1; i <= 9; i++){
                    stmt.setInt(index++, tabelaMagia.getEspacoDeMagia(i));
                }

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
