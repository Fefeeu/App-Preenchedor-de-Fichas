package br.rpp.sql;


import br.rpp.magias.Magia;
import br.rpp.magias.MagiaCura;
import br.rpp.magias.MagiaDano;

import java.sql.*;
import java.util.Objects;

public abstract class SQLMagia {
    public static void createMagia(Magia magia, int idFicha) {
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO " + Tabelas.MAGIA + " (" +
                "idMagia, ficha_idFicha, tipo, " +
                "nome, descricao, nivel, tempoConjuracao, duracao, " +
                "alcance, area, escola, tipoAcerto, ladoDado, numeroDados" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Chaves primárias (exatamente como na tabela)
            stmt.setInt(index++, magia.getIdMagia());
            stmt.setInt(index++, idFicha);

            // Campos obrigatórios com valores padrão
            stmt.setString(index++, magia.tipo.toLowerCase());
            stmt.setString(index++, magia.nome);
            stmt.setString(index++, magia.descricao);
            stmt.setInt(index++, magia.nivel);
            stmt.setString(index++, magia.tempoConjuracao);
            stmt.setString(index++, magia.duracao);
            stmt.setString(index++, magia.alcance);
            stmt.setString(index++, magia.area);
            stmt.setString(index++, magia.escola);
            stmt.setString(index++, magia.tipoAcerto);

            // Campos com valores padrão no banco
            if (magia instanceof MagiaDano magiaDano) {
                stmt.setInt(index++, magiaDano.dadoDano);    // ladoDado
                stmt.setInt(index, magiaDano.quantidadeDado); // numeroDados
            } else if (magia instanceof MagiaCura magiaCura) {
                stmt.setInt(index++, magiaCura.dadoCura);    // ladoDado
                stmt.setInt(index, magiaCura.quantidadeDado); // numeroDados
            } else {
                stmt.setInt(index++, -1);    // lado padrao
                stmt.setInt(index, -1); // quantidade padrao
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Magia readItem(int idMagia) {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.MAGIA + " WHERE idItem = ?";

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
                                    rs.getString("tipo"),
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
                        }

                        // -------------------------- MAGIA DE CURA ----------------------------
                        case "cura": {
                            magia = new MagiaCura(
                                    rs.getInt("idMagia"),
                                    rs.getString("tipo"),
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
                        }

                        // -------------------------- MAGIA DE EFEITO --------------------------
                        default: {
                            magia = new Magia(
                                    rs.getInt("idMagia"),
                                    rs.getString("tipo"),
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
