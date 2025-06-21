package com.rpp.api.controller.pack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {
    private int pc; // Peças de Cobre
    private int pp; // Peças de Prata
    private int pe; // Peças de Electro
    private int po; // Peças de Ouro
    private int pl; // Peças de Platina
    private String itens;
}
