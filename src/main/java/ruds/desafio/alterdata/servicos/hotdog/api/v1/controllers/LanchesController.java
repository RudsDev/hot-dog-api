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

import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.LancheInput;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output.LancheOutput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Lanche;
import ruds.desafio.alterdata.servicos.hotdog.domain.services.LancheService;

@RestController
@RequestMapping("/v1/lanches")
public class LanchesController {

    @Autowired
    LancheService service;

    @PostMapping()
    public ResponseEntity<Lanche> save(
        @Valid @RequestBody LancheInput input,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        Lanche lanche = service.save(input);
        return new ResponseEntity<Lanche>(lanche, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<LancheOutput> update(
        @Valid @RequestBody LancheInput input,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        LancheOutput ingrediente = service.update(input);
        return new ResponseEntity<LancheOutput>(ingrediente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancheOutput> getById(
        @Valid @PathVariable Long id,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        LancheOutput output = service.getById(id);
        return new ResponseEntity<LancheOutput>(output, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<LancheOutput>> getAll() {
        List<LancheOutput> outputs = service.getAll();
        return new ResponseEntity<List<LancheOutput>>(outputs, HttpStatus.OK);
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
