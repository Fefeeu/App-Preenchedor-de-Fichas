package br.rpp.sql;

import br.rpp.auxiliar.enuns.Tabelas;
import br.rpp.ficha.Ficha;
import br.rpp.ficha.TabelaMagia;
import br.rpp.magias.Magia;

import java.sql.*;
import java.util.ArrayList;
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

    public static TabelaMagia readMagiaUser(int idTabelaMagia, Ficha ficha) {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.MAGIAUSER + " WHERE idMagiaUser = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idTabelaMagia);
            TabelaMagia leituraTabela = null;

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    leituraTabela = new TabelaMagia(
                            rs.getInt("idMagiaUser"),
                            ficha
                    );
                    // for para setar todos os espaços de nivel de magia
                    for(int i = 1; i<=9; i++){
                        leituraTabela.setEspacoDeMagia(i, rs.getInt("nivel" + i));
                    }
                }
            }

            String sqlMagias = "SELECT idMagia FROM " + Tabelas.MAGIA + " WHERE magiaUser_idMagiaUser = ?";
            ArrayList<Integer> magiasIds = new ArrayList<>();

            try (PreparedStatement stmtMagias = connection.prepareStatement(sqlMagias)) {
                stmtMagias.setInt(1, idTabelaMagia);

                try (ResultSet rsMagias = stmtMagias.executeQuery()) {
                    while (rsMagias.next()) {
                        magiasIds.add(rsMagias.getInt("idMagia"));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (Integer magiasId : magiasIds) {
                Magia magia = SQLMagia.readMagia(magiasId);
                if (leituraTabela != null) {
                        leituraTabela.registrarMagia(magia);
                }
            }
            return leituraTabela;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Retorna null se a tabelaMatgia não for encontrada
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
