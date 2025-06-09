package br.rpp.sql;

import br.rpp.ficha.Ficha;

import java.sql.*;
import java.util.ArrayList;

public abstract class SQLFicha {
    public static int createFicha(Ficha ficha) throws SQLException {
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO ficha (" +
                "  idFicha, user_idUser, vivo, nivel, deslocamento, dadoDeVida, pontosDeVidaBase, pontosDeVidaTemporario, inspiracao," +
                "  nomePersonagem, classe_idClasse, Raca_idRaca, antecedente, tendencia, xp, idade, altura, peso," +
                "  olho, pele, cabelo," +
                "  forca, destreza, constituicao, inteligencia, sabedoria, carisma," +
                "  p_forca, p_destreza, p_constituicao, p_inteligencia, p_sabedoria, p_carisma," +
                "  p_acrobacia, p_arcanismo, p_atletismo, p_atuacao, p_blefar, p_furtividade," +
                "  p_historia, p_intimidacao, p_intuicao, p_investigacao, p_lidarComAnimais, p_medicina," +
                "  p_natureza, p_percepcao, p_persuasao, p_prestigitacao, p_religiao, p_sobrevivencia," +
                "  historia, aparencia, personalidade, ideal, ligacao, defeitos" +
                ") VALUES (" +
                "  ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?" +
                ")";


        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Dados básicos (8 parâmetros)
            stmt.setInt(index++, ficha.getIdFicha()); // idFicha (auto-increment)
            stmt.setInt(index++, ficha.getIdUser()); // user_idUser (deve ser fornecido)
            stmt.setBoolean(index++, ficha.getEstado());
            stmt.setInt(index++, ficha.nivel);
            stmt.setFloat(index++, ficha.deslocamento);
            stmt.setInt(index++, ficha.dadoDeVida);
            stmt.setInt(index++, ficha.pontosVidaBase);
            stmt.setInt(index++, ficha.vidaTemporaria);
            stmt.setBoolean(index++, ficha.inspiracao);

            // Info personagem (9 parâmetros)
            stmt.setString(index++, ficha.caracteristicas.nomePersonagem);
            stmt.setString(index++, ficha.caracteristicas.idClasse);
            stmt.setString(index++, ficha.caracteristicas.idRaca);
            stmt.setString(index++, ficha.caracteristicas.antecedente);
            stmt.setString(index++, ficha.caracteristicas.tendencia);
            stmt.setInt(index++, ficha.caracteristicas.xp);
            stmt.setInt(index++, ficha.caracteristicas.idade);
            stmt.setFloat(index++, ficha.caracteristicas.altura);
            stmt.setFloat(index++, ficha.caracteristicas.peso);

            // Aparência (3 parâmetros)
            stmt.setString(index++, ficha.caracteristicas.olho);
            stmt.setString(index++, ficha.caracteristicas.pele);
            stmt.setString(index++, ficha.caracteristicas.cabelo);

            // Atributos (6 parâmetros)
            stmt.setInt(index++, ficha.atributos.get("forca"));
            stmt.setInt(index++, ficha.atributos.get("destreza"));
            stmt.setInt(index++, ficha.atributos.get("constituicao"));
            stmt.setInt(index++, ficha.atributos.get("inteligencia"));
            stmt.setInt(index++, ficha.atributos.get("sabedoria"));
            stmt.setInt(index++, ficha.atributos.get("carisma"));

            // Proficiências em atributos (6 parâmetros)
            stmt.setBoolean(index++, ficha.pericias.get("forca"));
            stmt.setBoolean(index++, ficha.pericias.get("destreza"));
            stmt.setBoolean(index++, ficha.pericias.get("constituicao"));
            stmt.setBoolean(index++, ficha.pericias.get("inteligencia"));
            stmt.setBoolean(index++, ficha.pericias.get("sabedoria"));
            stmt.setBoolean(index++, ficha.pericias.get("carisma"));

            // Perícias (18 parâmetros)
            stmt.setBoolean(index++, ficha.pericias.get("acrobacia"));
            stmt.setBoolean(index++, ficha.pericias.get("arcanismo"));
            stmt.setBoolean(index++, ficha.pericias.get("atletismo"));
            stmt.setBoolean(index++, ficha.pericias.get("atuacao"));
            stmt.setBoolean(index++, ficha.pericias.get("blefar"));
            stmt.setBoolean(index++, ficha.pericias.get("furtividade"));
            stmt.setBoolean(index++, ficha.pericias.get("historia"));
            stmt.setBoolean(index++, ficha.pericias.get("intimidacao"));
            stmt.setBoolean(index++, ficha.pericias.get("intuicao"));
            stmt.setBoolean(index++, ficha.pericias.get("investigacao"));
            stmt.setBoolean(index++, ficha.pericias.get("lidar_com_animais"));
            stmt.setBoolean(index++, ficha.pericias.get("medicina"));
            stmt.setBoolean(index++, ficha.pericias.get("natureza"));
            stmt.setBoolean(index++, ficha.pericias.get("percepcao"));
            stmt.setBoolean(index++, ficha.pericias.get("persuasao"));
            stmt.setBoolean(index++, ficha.pericias.get("prestigitacao"));
            stmt.setBoolean(index++, ficha.pericias.get("religiao"));
            stmt.setBoolean(index++, ficha.pericias.get("sobrevivencia"));

            // Descrições (6 parâmetros - agora incluindo personalidade)
            stmt.setString(index++, ficha.descricao.historia);
            stmt.setString(index++, ficha.descricao.aparencia);
            stmt.setString(index++, ficha.descricao.personalidade);
            stmt.setString(index++, ficha.descricao.ideal);
            stmt.setString(index++, ficha.descricao.ligacao);
            stmt.setString(index, ficha.descricao.defeitos);

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }

    public static Ficha readFicha(int idFicha) throws SQLException {
        Connection connection = BD.getConnection();

        String sql = "SELECT * FROM ficha WHERE idFicha = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idFicha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criar a ficha com todos os parâmetros do construtor
                    Ficha ficha = new Ficha(
                            rs.getInt("idUser"),
                            rs.getBoolean("vivo"),
                            rs.getInt("nivel"),
                            rs.getString("nomePersonagem"),
                            rs.getString("classe_idClasse"),  // metodo busca classe
                            rs.getString("antecedente"),
                            "", // userName - ajustar conforme necessário
                            rs.getString("Raca_idRaca"),    // metodo busca raca
                            rs.getString("tendencia"),
                            rs.getInt("xp"),
                            rs.getInt("idade"),
                            rs.getFloat("altura"),
                            rs.getFloat("peso"),
                            rs.getString("olho"),
                            rs.getString("pele"),
                            rs.getString("cabelo"),
                            new ArrayList<>(), // idiomas
                            new ArrayList<>(), // proeficiencia
                            rs.getInt("forca"),
                            rs.getInt("destreza"),
                            rs.getInt("constituicao"),
                            rs.getInt("inteligencia"),
                            rs.getInt("sabedoria"),
                            rs.getInt("carisma"),
                            rs.getFloat("deslocamento"),
                            rs.getInt("pontosDeVidaBase"),
                            rs.getInt("pontosDeVidaTemporario"),
                            rs.getInt("dadoDeVida"),
                            rs.getString("historia"),
                            rs.getString("aparencia"),
                            rs.getString("personalidade"),
                            rs.getString("ideal"),
                            rs.getString("ligacao"),
                            rs.getString("defeitos")
                    );

                    // Configurar todas as perícias

                    ficha.pericias.put("forca", rs.getBoolean("p_forca"));
                    ficha.pericias.put("destreza", rs.getBoolean("p_destreza"));
                    ficha.pericias.put("constituicao", rs.getBoolean("p_constituicao"));
                    ficha.pericias.put("inteligencia", rs.getBoolean("p_inteligencia"));
                    ficha.pericias.put("sabedoria", rs.getBoolean("p_sabedoria"));
                    ficha.pericias.put("carisma", rs.getBoolean("p_carisma"));
                    ficha.pericias.put("acrobacia", rs.getBoolean("p_acrobacia"));
                    ficha.pericias.put("arcanismo", rs.getBoolean("p_arcanismo"));
                    ficha.pericias.put("atletismo", rs.getBoolean("p_atletismo"));
                    ficha.pericias.put("atuacao", rs.getBoolean("p_atuacao"));
                    ficha.pericias.put("blefar", rs.getBoolean("p_blefar"));
                    ficha.pericias.put("furtividade", rs.getBoolean("p_furtividade"));
                    ficha.pericias.put("historia", rs.getBoolean("p_historia"));
                    ficha.pericias.put("intimidacao", rs.getBoolean("p_intimidacao"));
                    ficha.pericias.put("intuicao", rs.getBoolean("p_intuicao"));
                    ficha.pericias.put("investigacao", rs.getBoolean("p_investigacao"));
                    ficha.pericias.put("lidar_com_animais", rs.getBoolean("p_lidarComAnimais"));
                    ficha.pericias.put("medicina", rs.getBoolean("p_medicina"));
                    ficha.pericias.put("natureza", rs.getBoolean("p_natureza"));
                    ficha.pericias.put("percepcao", rs.getBoolean("p_percepcao"));
                    ficha.pericias.put("persuasao", rs.getBoolean("p_persuasao"));
                    ficha.pericias.put("prestigitacao", rs.getBoolean("p_prestigitacao"));
                    ficha.pericias.put("religiao", rs.getBoolean("p_religiao"));
                    ficha.pericias.put("sobrevivencia", rs.getBoolean("p_sobrevivencia"));

                    return ficha;
                }
            }
        }
        return null; // Retorna null se a ficha não for encontrada
    }

    public static void deleteFicha(int id) throws SQLException {
        Connection connection = BD.getConnection();

        String sql = "DELETE FROM ficha WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o parâmetro ID na query
            stmt.executeUpdate(); // Executa a exclusão
        }
        // O PreparedStatement é automaticamente fechado ao sair do bloco try
    }

    public static int gerarIdFicha() {
        final String sql = "SELECT COALESCE(MAX(idFicha), 0) + 1 FROM ficha";

        try (Connection connection = BD.getConnection()) {
            assert connection != null;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                if (!rs.next()) {
                    throw new SQLException("Nenhum resultado encontrado ao gerar ID para ficha");
                }
                return rs.getInt(1);
            } catch (SQLException e) {
                System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
                throw new SQLException("Falha ao executar consulta para gerar ID", e);
            }

        } catch (SQLException e) {
            System.err.println("Erro de conexão com o banco de dados: " + e.getMessage());
            try {
                throw new SQLException("Falha na conexão ao tentar gerar ID para ficha", e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}