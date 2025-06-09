package br.rpp.sql;

import br.rpp.dado.Dado;
import br.rpp.inventario.item.*;
import br.rpp.magias.Magia;
import br.rpp.magias.MagiaCura;
import br.rpp.magias.MagiaDano;

import java.sql.*;

public abstract class SQLMagia {
    public static int createMagia(Magia magia, int idUser, int idFicha) throws SQLException {
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO magia (" +
                "idmagia, ficha_user_idUser, ficha_idFicha, tipo, " +
                "nome, descricao, nivel, tempoConjuracao, duracao, " +
                "alcance, area, escola, tipoAcerto, ladoDado, numeroDados" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Chaves primárias (exatamente como na tabela)
            stmt.setInt(index++, magia.getIdMagia());
            stmt.setInt(index++, idUser);
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

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            throw new SQLException("Falha ao obter ID gerado para a magia");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Magia readItem(int idMagia) throws SQLException {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM magia WHERE idItem = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMagia);

            try (ResultSet rs = stmt.executeQuery()) {
                Magia magia;
                if (rs.next()) {
                    switch (rs.getString("tipo")) {
                        // -------------------------- MAGIA DE DANO ----------------------------
                        case "dano": {
                            magia = new MagiaDano(
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
        }
        return null; // Retorna null se a magia não for encontrada
    }

    public static void deleteMagia(int id) throws SQLException {
        Connection connection = BD.getConnection();
        String sql = "DELETE FROM magia WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static int gerarIdMagia() {
        final String sql = "SELECT COALESCE(MAX(idMagia), 0) + 1 FROM magia";

        try (Connection connection = BD.getConnection()) {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("Conexão com o banco de dados não está disponível");
            }

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                if (!rs.next()) {
                    throw new SQLException("Nenhum registro encontrado na tabela magia");
                }
                return rs.getInt(1);

            } catch (SQLException e) {
                System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
                throw new SQLException("Falha ao executar consulta para gerar ID", e);
            }

        } catch (SQLException e) {
            System.err.println("Erro de conexão com o banco de dados: " + e.getMessage());
            try {
                throw new SQLException("Falha na conexão ao tentar gerar ID para magia", e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
