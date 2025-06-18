package com.rpp.api.controller;

import com.rpp.api.domain.Sheet;
import org.springframework.web.bind.annotation.*;

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
    public String create(@RequestBody Sheet body){
        return "Ficha criada";
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
