package br.rpp.sql;


import br.rpp.auxiliar.enuns.Tabelas;
import br.rpp.ficha.Ficha;
import br.rpp.magias.Magia;
import br.rpp.magias.MagiaCura;
import br.rpp.magias.MagiaDano;

import java.sql.*;
import java.util.Objects;

public abstract class SQLMagia {
    public static void createMagia(Magia magia, Ficha ficha) {
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO " + Tabelas.MAGIA + " (" +
                "idMagia, magiaUser_idMagiaUser, tipo, " +
                "nome, descricao, nivel, tempoConjuracao, duracao, " +
                "alcance, area, escola, tipoAcerto, ladoDado, numeroDados" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Chaves primárias (exatamente como na tabela)
            stmt.setInt(index++, magia.getIdMagia());
            stmt.setInt(index++, ficha.getMagias().getId());

            // Campos obrigatórios com valores padrão
            stmt.setString(index++, magia.getTipo());
            stmt.setString(index++, magia.getNome());
            stmt.setString(index++, magia.getDescricao());
            stmt.setInt(index++, magia.getNivel());
            stmt.setString(index++, magia.getTempoConjuracao());
            stmt.setString(index++, magia.getDuracao());
            stmt.setString(index++, magia.getAlcance());
            stmt.setString(index++, magia.getArea());
            stmt.setString(index++, magia.getEscola());
            stmt.setString(index++, magia.getTipoAcerto());

            // Campos com valores padrão no banco
            if (magia instanceof MagiaDano magiaDano) {
                stmt.setInt(index++, magiaDano.getDadoDano());    // ladoDado
                stmt.setInt(index, magiaDano.getQuantidadeDado()); // numeroDados
            } else if (magia instanceof MagiaCura magiaCura) {
                stmt.setInt(index++, magiaCura.getDadoCura());    // ladoDado
                stmt.setInt(index, magiaCura.getQuantidadeDado()); // numeroDados
            } else {
                stmt.setInt(index++, -1);    // lado padrao
                stmt.setInt(index, -1); // quantidade padrao
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Magia readMagia(int idMagia) {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.MAGIA + " WHERE idMagia = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idMagia);

            try (ResultSet rs = stmt.executeQuery()) {
                Magia magia;
                if (rs.next()) {
                    switch (rs.getString("tipo")) {
                        // -------------------------- MAGIA DE DANO ----------------------------
                        case "dano": {
                            magia = new MagiaDano(
                                    rs.getInt("idMagia"),
                                    rs.getString("nome"),
                                    rs.getString("descricao"),
                                    rs.getInt("nivel"),
                                    rs.getString("tempoConjuracao"),
                                    rs.getString("duracao"),
                                    rs.getString("alcance"),
                                    rs.getString("area"),
                                    rs.getString("escola"),
                                    rs.getString("tipoAcerto"),
                                    rs.getInt("ladoDado"),
                                    rs.getInt("numeroDados")
                            );
                            break;
                        }

                        // -------------------------- MAGIA DE CURA ----------------------------
                        case "cura": {
                            magia = new MagiaCura(
                                    rs.getInt("idMagia"),
                                    rs.getString("nome"),
                                    rs.getString("descricao"),
                                    rs.getInt("nivel"),
                                    rs.getString("tempoConjuracao"),
                                    rs.getString("duracao"),
                                    rs.getString("alcance"),
                                    rs.getString("area"),
                                    rs.getString("escola"),
                                    rs.getString("tipoAcerto"),
                                    rs.getInt("ladoDado"),
                                    rs.getInt("numeroDados")
                            );
                            break;
                        }

                        // -------------------------- MAGIA DE EFEITO --------------------------
                        default: {
                            magia = new Magia(
                                    rs.getInt("idMagia"),
                                    rs.getString("nome"),
                                    rs.getString("descricao"),
                                    rs.getInt("nivel"),
                                    rs.getString("tempoConjuracao"),
                                    rs.getString("duracao"),
                                    rs.getString("alcance"),
                                    rs.getString("area"),
                                    rs.getString("escola"),
                                    rs.getString("tipoAcerto")
                            );
                        }
                    }

                    return magia;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se a magia não for encontrada
    }

    public static void deleteMagia(int id) {
        Connection connection = BD.getConnection();
        String sql = "DELETE FROM " + Tabelas.MAGIA + " WHERE idMagia = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
