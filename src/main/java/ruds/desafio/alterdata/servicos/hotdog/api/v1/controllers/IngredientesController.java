package ruds.desafio.alterdata.servicos.hotdog.api.v1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.IngredienteInput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Ingrediente;
import ruds.desafio.alterdata.servicos.hotdog.domain.services.IngredienteService;

@RestController
@RequestMapping("/v1/ingredientes")
public class IngredientesController {

    @Autowired
    IngredienteService service;

    @GetMapping()
    public ResponseEntity<List<Ingrediente>> getAll() {
        List<Ingrediente> ingredientes = service.getAll();
        return new ResponseEntity<List<Ingrediente>>(ingredientes, HttpStatus.OK);
    } 

    @PostMapping()
    public ResponseEntity<Ingrediente> save(
        @Valid @RequestBody IngredienteInput input,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        Ingrediente ingrediente = service.save(input);        
        return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Ingrediente> update(
        @Valid @RequestBody IngredienteInput input,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        Ingrediente ingrediente = service.update(input);
        return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getById(
        @Valid @PathVariable Long id,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        Ingrediente ingrediente = service.getById(id);
        return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(
        @Valid @PathVariable Long id,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        service.delete(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
