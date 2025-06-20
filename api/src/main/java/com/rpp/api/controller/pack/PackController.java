package com.rpp.api.controller.pack;

import com.rpp.api.domain.Sheet;
import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;
import com.rpp.api.domain.br.rpp.ficha.Ficha;
import com.rpp.api.domain.br.rpp.ficha.FichaResumoDTO;
import com.rpp.api.domain.br.rpp.ficha.TabelaMagia;
import com.rpp.api.domain.br.rpp.inventario.Inventario;
import com.rpp.api.domain.br.rpp.sql.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rpp.api.controller.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/pack")
public class PackController {

    @GetMapping
    public String pack(){
        return "You are in pack";
    }

    @PostMapping("/test")
    public String test(@RequestBody Sheet body){
        return "Você criou o personagem: " + body.getCharacterName() + " de classe " + body.getCharacterClass() + " e raça " + body.getCharacterRace();
    }

    // Get sheet by id
    @GetMapping("/sheet/{id}")
    public ResponseEntity<ApiResponse<Ficha>> get(@PathVariable int id){
        boolean success;
        String message;
        Ficha data;

        try {
            Ficha ficha = SQLFicha.readFicha(id);

            if(ficha == null){
                success = false;
                message = "Ficha não encontrada";
                data = null;
            } else {
                success = true;
                message = "Ficha de id " + id + " encontrada";
                data = ficha;
            }

            ApiResponse<Ficha> response = new ApiResponse<>(
                    success,
                    message,
                    data
            );

            return ResponseEntity.ok(response);
        } catch (Exception error){
            ApiResponse<Ficha> errorResponse = new ApiResponse<>(
                    false,
                    "Erro ao criar ficha: " + error.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Get all sheets by user id
    @GetMapping("/sheets/{id}")
    public ApiResponse<List<FichaResumoDTO>> getSheetsByUserId(@PathVariable int id){
        try {
            List<FichaResumoDTO> fichas = SQLFicha.getFichasByUser(id);

            return new ApiResponse<>(
                    true,
                    "Fichas do usuário de id " + id,
                    fichas
            );
        } catch (Exception error){
            return new ApiResponse<>(
                    false,
                    "ocorreu um erro ao buscar suas fichas: " + error,
                    null
            );
        }
    }

    // Create sheet
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Ficha>> create(@RequestBody FichaCreateDTO dto) {
        try {
            Inventario inventario = new Inventario(BD.gerarId(Tabelas.INVENTARIO.toString()));
            inventario.setMoedas('c', dto.getInventario().getPc());
            inventario.setMoedas('p', dto.getInventario().getPp());
            inventario.setMoedas('e', dto.getInventario().getPe());
            inventario.setMoedas('o', dto.getInventario().getPo());
            inventario.setMoedas('l', dto.getInventario().getPl());
            SQLInventario.createInventario(inventario);

            Ficha ficha = new Ficha(
                    BD.gerarId(Tabelas.FICHA.toString()),
                    dto.getIdUser(),
                    true,
                    dto.getNivel(),
                    dto.getNomePersonagem(),
                    dto.getIdClasse(),
                    dto.getAntecedente(),
                    "Nome do Usuário",
                    dto.getIdRaca(),
                    dto.getTendencia(),
                    dto.getXp(),
                    25,
                    1.80f,
                    70.0f,
                    "Castanhos",
                    "Branca",
                    "Preto",
                    dto.getIdiomas(),
                    dto.getDescricao().getProficiencia(),
                    dto.getAtributos().get("forca"),
                    dto.getAtributos().get("destreza"),
                    dto.getAtributos().get("constituicao"),
                    dto.getAtributos().get("inteligencia"),
                    dto.getAtributos().get("sabedoria"),
                    dto.getAtributos().get("carisma"),
                    dto.getDeslocamento(),
                    dto.getPontosVidaBase(),
                    dto.getVidaTemporaria(),
                    dto.getDadoDeVida(),
                    "História padrão",
                    "Aparência padrão",
                    dto.getDescricao().getPersonalidade(),
                    dto.getDescricao().getIdeal(),
                    dto.getDescricao().getLigacao(),
                    dto.getDescricao().getDefeito(),
                    false
            );

            ficha.setInventario(inventario);

            if (dto.getPericias() != null) {
                dto.getPericias().forEach(ficha::setPericia);
            }

            TabelaMagia magias = new TabelaMagia(BD.gerarId(Tabelas.MAGIAUSER.toString()), ficha);
            SQLMagiaUser.createMagiaUser(magias);

            ficha.setMagias(magias);

            SQLFicha.createFicha(ficha);

            ApiResponse<Ficha> response = new ApiResponse<>(
                    true,
                    "Ficha criada com sucesso",
                    ficha
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Ficha> errorResponse = new ApiResponse<>(
                    false,
                    "Erro ao criar ficha: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Update sheet
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Ficha>> update(@PathVariable int id, @RequestBody FichaCreateDTO dto){

        try {
            Ficha fichaAntiga = SQLFicha.readFicha(id);

            Inventario inventario = fichaAntiga.getInventario();
            inventario.setMoedas('c', dto.getInventario().getPc());
            inventario.setMoedas('p', dto.getInventario().getPp());
            inventario.setMoedas('e', dto.getInventario().getPe());
            inventario.setMoedas('o', dto.getInventario().getPo());
            inventario.setMoedas('l', dto.getInventario().getPl());

            SQLInventario.updateInventario(inventario);

            Ficha fichaAtualizada = new Ficha(
                    id,
                    dto.getIdUser(),
                    true, // estado
                    dto.getNivel(),
                    dto.getNomePersonagem(),
                    dto.getIdClasse(),
                    dto.getAntecedente(),
                    "Nome do Usuário",
                    dto.getIdRaca(),
                    dto.getTendencia(),
                    dto.getXp(),
                    25,
                    1.80f,
                    70.0f,
                    "Castanhos",
                    "Branca",
                    "Preto",
                    dto.getIdiomas(),
                    dto.getDescricao().getProficiencia(),
                    dto.getAtributos().get("forca"),
                    dto.getAtributos().get("destreza"),
                    dto.getAtributos().get("constituicao"),
                    dto.getAtributos().get("inteligencia"),
                    dto.getAtributos().get("sabedoria"),
                    dto.getAtributos().get("carisma"),
                    dto.getDeslocamento(),
                    dto.getPontosVidaBase(),
                    dto.getVidaTemporaria(),
                    dto.getDadoDeVida(),
                    "História padrão",
                    "Aparência padrão",
                    dto.getDescricao().getPersonalidade(),
                    dto.getDescricao().getIdeal(),
                    dto.getDescricao().getLigacao(),
                    dto.getDescricao().getDefeito(),
                    true
            );

            // 4. Manter os relacionamentos existentes
            fichaAtualizada.setInventario(new Inventario(fichaAntiga.getInventario().getId()));
            fichaAtualizada.setMagias(new TabelaMagia(fichaAntiga.getMagias().getId(), fichaAtualizada));

            // 5. Atualizar perícias se fornecidas
            if (dto.getPericias() != null) {
                dto.getPericias().forEach(fichaAtualizada::setPericia);
            }

            SQLFicha.updateFicha(fichaAtualizada);

            ApiResponse<Ficha> response = new ApiResponse<>(
                    true,
                    "Ficha de id " + id + "atualizada com sucesso",
                    fichaAtualizada
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Ficha> errorResponse = new ApiResponse<>(
                    false,
                    "Erro ao atualizar a ficha: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Delete sheet
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable int id){
        try {
            SQLFicha.deleteFicha(id);

            ApiResponse<Integer> response = new ApiResponse<>(
                    true,
                    "Ficha de id " + id + " deletada com sucesso",
                    id
            );

            return ResponseEntity.ok(response);
        } catch (Exception error){
            ApiResponse<Integer> errorResponse = new ApiResponse<>(
                    false,
                    "Erro ao deletar ficha: " + error.getMessage(),
                    id
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
