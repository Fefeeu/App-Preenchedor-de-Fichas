package br.rpp.sql;

import br.rpp.auxiliar.enuns.Tabelas;
import br.rpp.inventario.Inventario;
import br.rpp.inventario.item.*;

import java.sql.*;
import java.util.Objects;

public abstract class SQLItem {
    public static void createItem(Inventario inventario, Item item) {
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO " + Tabelas.ITEM + " (" +
            "iditem, inventario_idInventario, tipo, " +
            "nome, descricao, peso, moeda, preco, " +
            "usosMaximo, usos, efeito, bonus, bonus_ca, " +
            "proficiencia, numeroDeDados, dadoDeDano, atributo" +
        ") VALUES (" +
            "?, ?, ?, " +
            "?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, " +
            "?, ?, ?, ?" +
        ")";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Dados básicos (3 parâmetros - chaves primárias)
            stmt.setInt(index++, item.getId()); // iditem (auto-increment)
            stmt.setInt(index++, inventario.getId()); // inventario_idInventario

            // Informações principais (6 parâmetros)
            stmt.setString(index++, item.getTipo());
            stmt.setString(index++, item.getNome());
            stmt.setString(index++, item.getDescricao());
            stmt.setFloat(index++, item.getPeso());
            stmt.setString(index++, String.valueOf(item.getMoeda()));
            stmt.setInt(index++, item.getPreco());

            switch (item) {
                // -------------------------- ITEM CONSUMIVEL --------------------------
                case ItemConsumivel itemConsumivel -> {
                    stmt.setInt(index++, itemConsumivel.getUsosMaximo());
                    stmt.setInt(index++, itemConsumivel.getUsos());
                    stmt.setString(index++, "");// efeito

                    stmt.setInt(index++, -1);   // bonus

                    stmt.setInt(index++, -1); // bonus CA

                    stmt.setBoolean(index++, false);
                    stmt.setInt(index++, -1);
                    stmt.setInt(index++, -1);
                    stmt.setString(index, "");
                }

                // -------------------------- ITEM MAGICO ------------------------------
                case ItemMagico itemMagico -> {
                    stmt.setInt(index++, itemMagico.getCargasMaxima());
                    stmt.setInt(index++, itemMagico.getCargas());
                    stmt.setString(index++, itemMagico.getEfeito());
                    stmt.setInt(index++, -1);   // bonus

                    stmt.setInt(index++, -1); // bonus CA

                    stmt.setBoolean(index++, false);
                    stmt.setInt(index++, -1);
                    stmt.setInt(index++, -1);
                    stmt.setString(index, "");
                }

                // -------------------------- ITEM EQUIPAVEL ---------------------------
                case Equipavel itemEquipavel -> {
                    if (item instanceof EquipavelMagico itemEquipavelMagico) {
                        stmt.setInt(index++, itemEquipavelMagico.getCargasMaxima());
                        stmt.setInt(index++, itemEquipavelMagico.getCargas());
                        stmt.setString(index++, itemEquipavelMagico.getEfeito());
                        stmt.setInt(index++, itemEquipavelMagico.getBonus());
                    } else {
                        stmt.setInt(index++, -1);   // cargas maximas
                        stmt.setInt(index++, -1);   // cargas
                        stmt.setString(index++, "");// efeito
                        stmt.setInt(index++, -1);   // bonus
                    }
                    stmt.setInt(index++, itemEquipavel.getBonusCA());
                    stmt.setBoolean(index++, itemEquipavel.getProficiencia());
                    stmt.setInt(index++, -1);
                    stmt.setInt(index++, 1);
                    stmt.setString(index, "");
                }

                // -------------------------- ITEM ARMA --------------------------------
                case Arma itemArma -> {
                    if (itemArma instanceof ArmaMagica itemArmaMagica) {
                        stmt.setInt(index++, itemArmaMagica.getCargasMaxima());
                        stmt.setInt(index++, itemArmaMagica.getCargas());
                        stmt.setString(index++, itemArmaMagica.getEfeito());
                        stmt.setInt(index++, itemArmaMagica.getBonus());
                    } else {
                        stmt.setInt(index++, -1);   // cargas maximas
                        stmt.setInt(index++, -1);   // cargas
                        stmt.setString(index++, "");// efeito
                        stmt.setInt(index++, -1);   // bonus
                    }
                    stmt.setInt(index++, -1); // bonus CA

                    stmt.setBoolean(index++, itemArma.isProficiencia());
                    stmt.setInt(index++, itemArma.getQuantidadeDeDados());
                    stmt.setInt(index++, itemArma.getDadoDeDano());
                    stmt.setString(index, itemArma.getAtributo());
                }

                // -------------------------- ITEM PADRAO ------------------------------
                default -> {
                    stmt.setInt(index++, -1);   // cargas maximas
                    stmt.setInt(index++, -1);   // cargas
                    stmt.setString(index++, "");// efeito
                    stmt.setInt(index++, -1);   // bonus
                    stmt.setInt(index++, -1); // bonus CA
                    stmt.setBoolean(index++, false);
                    stmt.setInt(index++, -1);
                    stmt.setInt(index++, -1);
                    stmt.setString(index, "");
                }
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Item readItem(int idItem) {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM " + Tabelas.ITEM + " WHERE idItem = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idItem);

            try (ResultSet rs = stmt.executeQuery()) {
                Item item;
                if (rs.next()) {
                    switch (rs.getString("tipo")) {
                        // -------------------------- ITEM CONSUMIVEL --------------------------
                        case "consumivel": {
                            item = new ItemConsumivel(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("usos")
                            );
                        }

                        // -------------------------- ITEM MAGICO ------------------------------
                        case "magico": {
                            item = new ItemMagico(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getString("efeito"),
                                rs.getInt("usos")
                            );
                        }

                        // -------------------------- ITEM EQUIPAVEL ---------------------------
                        case "equipavel": {
                            item = new Equipavel(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("bonus_ca"),
                                rs.getBoolean("proficiencia")
                            );
                        }

                        // -------------------------- ITEM EQUIPAVEL MAGICO --------------------
                        case "equipavelMagico": {
                            item = new EquipavelMagico(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("bonus_ca"),
                                rs.getBoolean("proficiencia"),
                                rs.getString("efeito"),
                                rs.getInt("usos"),
                                rs.getInt("bonus")
                            );
                        }

                        // -------------------------- ITEM ARMA --------------------------------
                        case "arma": {
                            item = new Arma(
                                rs.getInt("idItem"),
                                SQLFicha.readFicha(idItem),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("dadoDeDano"),
                                rs.getInt("numeroDeDados"),
                                rs.getString("atributo"),
                                rs.getBoolean("proficiencia")
                            );
                        }

                        // -------------------------- ITEM ARMA MAGICA -------------------------
                        case "armaMagica": {
                            item = new ArmaMagica(
                                rs.getInt("idItem"),
                                SQLFicha.readFicha(idItem),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("dadoDeDano"),
                                rs.getInt("numeroDeDados"),
                                rs.getString("atributo"),
                                rs.getBoolean("proficiencia"),
                                rs.getString("efeito"),
                                rs.getInt("usos"),
                                rs.getInt("bonus")
                            );
                        }

                        // -------------------------- ITEM PADRAO ------------------------------
                        default: {
                            item = new Item(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco")
                            );
                        }
                    }
                    return item;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se a ficha não for encontrada
    }

    public static void deleteItem(int id) {
        Connection connection = BD.getConnection();
        String sql = "DELETE FROM " + Tabelas.ITEM + " WHERE idItem = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o parâmetro ID na query
            stmt.executeUpdate(); // Executa a exclusão
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // O PreparedStatement é automaticamente fechado ao sair do bloco try
    }
}