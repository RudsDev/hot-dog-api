package dev.ruds.hotdog.api.v1.ingredientes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ruds.hotdog.domain.dtos.inputs.IngredientePrecoPartialRecord;
import dev.ruds.hotdog.domain.models.Ingrediente;
import dev.ruds.hotdog.domain.records.IngredienteRecord;
import dev.ruds.hotdog.domain.services.IngredientesService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/v1/ingredientes")
public class IngredientesController {

    @Autowired
    IngredientesService service;

    @PostMapping()
    public ResponseEntity<Ingrediente> create(
        @RequestBody IngredienteRecord input
    ) {
        var ingrediente = service.create(input);
        return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Ingrediente>> findAll() {
        var list = service.findAll(); 
        return new ResponseEntity<List<Ingrediente>>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> update(@PathVariable Long id, @RequestBody IngredienteRecord input) {       
        var ingrediente = service.update(id, input);
        return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
    }

    @PatchMapping("/preco")
    public ResponseEntity<String> updatePreco(@RequestBody IngredientePrecoPartialRecord record) {
        service.updatePreco(record);
        return new ResponseEntity<String>("Preço atualizado", HttpStatus.OK);
    }
}