package dev.ruds.hotdog.api.v1.ingredientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ruds.hotdog.domain.models.Ingrediente;
import dev.ruds.hotdog.domain.records.IngredienteRecord;
import dev.ruds.hotdog.utils.mappers.IngredienteMapper;

@RestController
@RequestMapping("/v1/ingredientes")
public class IngredientesController {

    @Autowired
    IngredienteMapper mapper;

    @PostMapping()
    public ResponseEntity<Ingrediente> create(
        @RequestBody IngredienteRecord input
    ) {
        var ingrediente = mapper.toEntity(input);
        return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
    }

}