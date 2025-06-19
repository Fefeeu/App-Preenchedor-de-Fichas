package com.rpp.api.controller.pack;

import com.rpp.api.domain.Sheet;
import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;
import com.rpp.api.domain.br.rpp.ficha.Ficha;
import com.rpp.api.domain.br.rpp.inventario.Inventario;
import com.rpp.api.domain.br.rpp.sql.BD;
import com.rpp.api.domain.br.rpp.sql.SQLFicha;
import com.rpp.api.domain.br.rpp.sql.SQLInventario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rpp.api.controller.response.ApiResponse;

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
    @GetMapping("/{id}")
    public String get(@PathVariable String id){
        return "Return sheet by ID: " + id;
    }

    // Get all sheets by user id
    @GetMapping("/sheets")
    public String getSheetsByUserId(@PathVariable String id){
        return "Return sheets by User ID: " + id;
    }

    // Create sheet
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Ficha>> create(@RequestBody FichaCreateDTO dto) {
        try {
            // 1. Criar e persistir o inventário
            Inventario inventario = new Inventario(BD.gerarId(Tabelas.INVENTARIO.toString()));
            SQLInventario.createInventario(inventario); // Persiste o inventário no BD

            // 2. Criar a ficha com o ID do inventário
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

            // 3. Associar o inventário à ficha
            ficha.setInventario(inventario);

            // 4. Persistir a ficha
            SQLFicha.createFicha(ficha);

            // Configurar perícias (se necessário)
            if (dto.getPericias() != null) {
                dto.getPericias().forEach(ficha::setPericia);
            }

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
    public String update(@PathVariable String id, @RequestBody Sheet body){
        return "Ficha editada";
    }

    // Delete sheet
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id, @RequestBody Sheet body){
        return "Ficha deletada";
    }
}
