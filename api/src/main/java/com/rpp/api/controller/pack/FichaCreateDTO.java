package com.rpp.api.controller.pack;
import com.rpp.api.domain.br.rpp.ficha.Classe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FichaCreateDTO {
    private String nomePersonagem;
    private String idClasse;
    private String idRaca;
    private String antecedente;
    private String tendencia;
    private int xp;
    private int nivel;
    private Map<String, Integer> atributos;
    private Map<String, Boolean> pericias;
    private String idiomas;
    private float deslocamento;
    private int pontosVidaBase;
    private int vidaTemporaria;
    private int dadoDeVida;
    private int idUser = 1;
    private DescricaoDTO descricao;
    private InventarioDTO inventario;
}

