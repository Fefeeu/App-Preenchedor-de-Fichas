package com.rpp.api.domain.br.rpp.ficha;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FichaResumoDTO {
    private int idFicha;
    private String nomePersonagem;
    private String classe;
    private String raca;
    private int nivel;
}
