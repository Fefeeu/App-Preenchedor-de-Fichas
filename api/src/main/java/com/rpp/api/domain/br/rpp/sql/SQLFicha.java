package com.rpp.api.domain.br.rpp.sql;

import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;
import com.rpp.api.domain.br.rpp.ficha.Caracteristica;
import com.rpp.api.domain.br.rpp.ficha.Descricao;
import com.rpp.api.domain.br.rpp.ficha.Ficha;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;

public abstract class SQLFicha {
    public static void createFicha(Ficha ficha){
        SQLFicha.saveFicha(ficha, "criar");
    }

    public static void updateFicha(Ficha ficha){
        SQLFicha.saveFicha(ficha, "atualizar");
    }

    private static void saveFicha(Ficha ficha, String metodo){
        Connection connection = BD.getConnection();
        String sql = null;
        switch (metodo) {
            case("criar"): {
                sql = "INSERT INTO " + Tabelas.FICHA + " (" +
                    "  idFicha, user_idUser, vivo, nivel, deslocamento, dadoDeVida, pontosDeVidaBase, pontosDeVidaTemporario, inspiracao," +
                    "  nomePersonagem, classe_idClasse, raca_idRaca, antecedente, tendencia, xp, idade, altura, peso," +
                    "  olho, pele, cabelo," +
                    "  forca, destreza, constituicao, inteligencia, sabedoria, carisma," +
                    "  p_forca, p_destreza, p_constituicao, p_inteligencia, p_sabedoria, p_carisma," +
                    "  p_acrobacia, p_arcanismo, p_atletismo, p_atuacao, p_blefar, p_furtividade," +
                    "  p_historia, p_intimidacao, p_intuicao, p_investigacao, p_lidarComAnimais, p_medicina," +
                    "  p_natureza, p_percepcao, p_persuasao, p_prestigitacao, p_religiao, p_sobrevivencia," +
                    "  historia, aparencia, personalidade, ideal, ligacao, defeitos, proficiencias, idiomas," +
                    "  inventario_idInventario, magiaUser_idMagiaUser" +
                    ") VALUES (" +
                    "  ?, ?, ?, ?, ?, ?, ?, ?, ?," +    // 9
                    "  ?, ?, ?, ?, ?, ?, ?, ?, ?," +    // 18
                    "  ?, ?, ?," +                      // 21
                    "  ?, ?, ?, ?, ?, ?," +             // 27
                    "  ?, ?, ?, ?, ?, ?," +             // 33
                    "  ?, ?, ?, ?, ?, ?," +             // 39
                    "  ?, ?, ?, ?, ?, ?," +             // 45
                    "  ?, ?, ?, ?, ?, ?," +             // 51
                    "  ?, ?, ?, ?, ?, ?, ?, ?," +       // 59
                    "  ?, ?" +                          // 61
                    ")";
                break;
            }

            case ("atualizar"): {
                sql = "UPDATE " + Tabelas.FICHA + " SET " +
                    "user_idUser = ?, vivo = ?, nivel = ?, deslocamento = ?, dadoDeVida = ?, " +
                    "pontosDeVidaBase = ?, pontosDeVidaTemporario = ?, inspiracao = ?, " +
                    "nomePersonagem = ?, classe_idClasse = ?, raca_idRaca = ?, antecedente = ?, " +
                    "tendencia = ?, xp = ?, idade = ?, altura = ?, peso = ?, olho = ?, pele = ?, " +
                    "cabelo = ?, forca = ?, destreza = ?, constituicao = ?, inteligencia = ?, " +
                    "sabedoria = ?, carisma = ?, p_forca = ?, p_destreza = ?, p_constituicao = ?, " +
                    "p_inteligencia = ?, p_sabedoria = ?, p_carisma = ?, p_acrobacia = ?, " +
                    "p_arcanismo = ?, p_atletismo = ?, p_atuacao = ?, p_blefar = ?, p_furtividade = ?, " +
                    "p_historia = ?, p_intimidacao = ?, p_intuicao = ?, p_investigacao = ?, " +
                    "p_lidarComAnimais = ?, p_medicina = ?, p_natureza = ?, p_percepcao = ?, " +
                    "p_persuasao = ?, p_prestigitacao = ?, p_religiao = ?, p_sobrevivencia = ?, " +
                    "historia = ?, aparencia = ?, personalidade = ?, ideal = ?, ligacao = ?, " +
                    "defeitos = ?, proficiencias = ?, idiomas = ?, inventario_idInventario = ?, " +
                    "magiaUser_idMagiaUser = ?" +
                    "WHERE idFicha = ?";  // Condição para identificar qual ficha atualizar
                break;
            }
            default: System.out.println("metodo invalido para salvar ficha" + metodo);
        }

        if (sql != null) {
            try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                int index = 1;

                // Dados básicos (8 parâmetros)
                if(metodo.equals("criar")){
                    stmt.setInt(index++, ficha.getIdFicha()); // idFicha (auto-increment)
                }
                stmt.setInt(index++, ficha.getIdUser()); // user_idUser (deve ser fornecido)
                stmt.setBoolean(index++, ficha.getEstado());
                stmt.setInt(index++, ficha.getNivel());
                stmt.setFloat(index++, ficha.getDeslocamento());
                stmt.setInt(index++, ficha.getDadoDeVida());
                stmt.setInt(index++, ficha.getPontosVidaBase());
                stmt.setInt(index++, ficha.getVidaTemporaria());
                stmt.setBoolean(index++, ficha.isInspiracao());

                Caracteristica caracteristicas = ficha.getCaracteristicas();

                // Info personagem (7 parâmetros)
                stmt.setString(index++, caracteristicas.getNomePersonagem());
                stmt.setString(index++, caracteristicas.getIdClasse());
                stmt.setString(index++, caracteristicas.getIdRaca());
                stmt.setString(index++, caracteristicas.getAntecedente());
                stmt.setString(index++, caracteristicas.getTendencia());
                stmt.setInt(index++, caracteristicas.getXp());
                stmt.setInt(index++, caracteristicas.getIdade());

                // Aparência (5 parâmetros)
                stmt.setFloat(index++, caracteristicas.getAltura());
                stmt.setFloat(index++, caracteristicas.getPeso());
                stmt.setString(index++, caracteristicas.getOlho());
                stmt.setString(index++, caracteristicas.getPele());
                stmt.setString(index++, caracteristicas.getCabelo());

                HashMap<String, Integer> atributos = ficha.getAtributos();

                // Atributos (6 parâmetros)
                stmt.setInt(index++, atributos.get("forca"));
                stmt.setInt(index++, atributos.get("destreza"));
                stmt.setInt(index++, atributos.get("constituicao"));
                stmt.setInt(index++, atributos.get("inteligencia"));
                stmt.setInt(index++, atributos.get("sabedoria"));
                stmt.setInt(index++, atributos.get("carisma"));

                HashMap<String, Boolean> listaPericias = ficha.getPericias();

                // Proficiências em atributos (6 parâmetros)
                stmt.setBoolean(index++, listaPericias.get("forca"));
                stmt.setBoolean(index++, listaPericias.get("destreza"));
                stmt.setBoolean(index++, listaPericias.get("constituicao"));
                stmt.setBoolean(index++, listaPericias.get("inteligencia"));
                stmt.setBoolean(index++, listaPericias.get("sabedoria"));
                stmt.setBoolean(index++, listaPericias.get("carisma"));

                // Perícias (18 parâmetros)
                stmt.setBoolean(index++, listaPericias.get("acrobacia"));
                stmt.setBoolean(index++, listaPericias.get("arcanismo"));
                stmt.setBoolean(index++, listaPericias.get("atletismo"));
                stmt.setBoolean(index++, listaPericias.get("atuacao"));
                stmt.setBoolean(index++, listaPericias.get("blefar"));
                stmt.setBoolean(index++, listaPericias.get("furtividade"));
                stmt.setBoolean(index++, listaPericias.get("historia"));
                stmt.setBoolean(index++, listaPericias.get("intimidacao"));
                stmt.setBoolean(index++, listaPericias.get("intuicao"));
                stmt.setBoolean(index++, listaPericias.get("investigacao"));
                stmt.setBoolean(index++, listaPericias.get("lidar_com_animais"));
                stmt.setBoolean(index++, listaPericias.get("medicina"));
                stmt.setBoolean(index++, listaPericias.get("natureza"));
                stmt.setBoolean(index++, listaPericias.get("percepcao"));
                stmt.setBoolean(index++, listaPericias.get("persuasao"));
                stmt.setBoolean(index++, listaPericias.get("prestigitacao"));
                stmt.setBoolean(index++, listaPericias.get("religiao"));
                stmt.setBoolean(index++, listaPericias.get("sobrevivencia"));

                Descricao descricao = ficha.getDescricao();

                // Descrições (6 parâmetros - agora incluindo personalidade)
                stmt.setString(index++, descricao.getHistoria());
                stmt.setString(index++, descricao.getAparencia());
                stmt.setString(index++, descricao.getPersonalidade());
                stmt.setString(index++, descricao.getIdeal());
                stmt.setString(index++, descricao.getLigacao());
                stmt.setString(index++, descricao.getDefeitos());

                // proficiencias (2 parâmetros)
                stmt.setString(index++, caracteristicas.getIdiomas());
                stmt.setString(index++, caracteristicas.getProeficiencias());

                // inventario (1 parâmetro)
                stmt.setInt(index++, ficha.getInventario().getId());

                // tabelaMagia ()
                stmt.setInt(index++, ficha.getMagias().getId());

                // se for uma atualizacao o id fica no final
                if(metodo.equals("atualizar")){
                    stmt.setInt(index, ficha.getIdFicha());
                }

                stmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Ficha readFicha(int idFicha){
        Connection connection = BD.getConnection();

        String sql = "SELECT * FROM " + Tabelas.FICHA + " WHERE idFicha = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idFicha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criar a ficha com todos os parâmetros do construtor
                    Ficha ficha = new Ficha(
                            rs.getInt("idFicha"),
                            rs.getInt("user_idUser"),
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
                            rs.getString("idiomas"),
                            rs.getString("proficiencias"),
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
                            rs.getString("defeitos"),
                            true
                    );

                    ficha.criarInventario(rs.getInt("inventario_idInventario"));
                    ficha.criarMagias(rs.getInt("magiaUser_idMagiaUser"));

                    // Configurar todas as perícias

                    ficha.setPericia("forca", rs.getBoolean("p_forca"));
                    ficha.setPericia("destreza", rs.getBoolean("p_destreza"));
                    ficha.setPericia("constituicao", rs.getBoolean("p_constituicao"));
                    ficha.setPericia("inteligencia", rs.getBoolean("p_inteligencia"));
                    ficha.setPericia("sabedoria", rs.getBoolean("p_sabedoria"));
                    ficha.setPericia("carisma", rs.getBoolean("p_carisma"));
                    ficha.setPericia("acrobacia", rs.getBoolean("p_acrobacia"));
                    ficha.setPericia("arcanismo", rs.getBoolean("p_arcanismo"));
                    ficha.setPericia("atletismo", rs.getBoolean("p_atletismo"));
                    ficha.setPericia("atuacao", rs.getBoolean("p_atuacao"));
                    ficha.setPericia("blefar", rs.getBoolean("p_blefar"));
                    ficha.setPericia("furtividade", rs.getBoolean("p_furtividade"));
                    ficha.setPericia("historia", rs.getBoolean("p_historia"));
                    ficha.setPericia("intimidacao", rs.getBoolean("p_intimidacao"));
                    ficha.setPericia("intuicao", rs.getBoolean("p_intuicao"));
                    ficha.setPericia("investigacao", rs.getBoolean("p_investigacao"));
                    ficha.setPericia("lidar_com_animais", rs.getBoolean("p_lidarComAnimais"));
                    ficha.setPericia("medicina", rs.getBoolean("p_medicina"));
                    ficha.setPericia("natureza", rs.getBoolean("p_natureza"));
                    ficha.setPericia("percepcao", rs.getBoolean("p_percepcao"));
                    ficha.setPericia("persuasao", rs.getBoolean("p_persuasao"));
                    ficha.setPericia("prestigitacao", rs.getBoolean("p_prestigitacao"));
                    ficha.setPericia("religiao", rs.getBoolean("p_religiao"));
                    ficha.setPericia("sobrevivencia", rs.getBoolean("p_sobrevivencia"));

                    return ficha;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se a ficha não for encontrada
    }

    public static void deleteFicha(int id){
        Connection connection = BD.getConnection();

        String sql = "DELETE FROM " + Tabelas.FICHA + " WHERE idFicha = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o parâmetro ID na query
            stmt.executeUpdate(); // Executa a exclusão
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // O PreparedStatement é automaticamente fechado ao sair do bloco try
    }
}