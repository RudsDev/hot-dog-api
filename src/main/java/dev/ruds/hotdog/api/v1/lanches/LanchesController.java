package dev.ruds.hotdog.api.v1.lanches;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ruds.hotdog.domain.models.Lanche;
import dev.ruds.hotdog.domain.records.LancheRecord;
import dev.ruds.hotdog.domain.services.LanchesService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/v1/lanches")
public class LanchesController {
    
    @Autowired
    LanchesService service;

    @PostMapping()
    public ResponseEntity<Lanche> create(
        @RequestBody LancheRecord input
    ) {
        Lanche lanche = service.create(input);
        return new ResponseEntity<Lanche>(lanche, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lanche> update(
        @PathVariable Long id,
        @RequestBody LancheRecord input
    ) {
        Lanche lanche = service.update(id, input);
        return new ResponseEntity<Lanche>(lanche, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Lanche>> findAll() {
        var list = service.findAll();
        return new ResponseEntity<List<Lanche>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lanche> findById(@PathVariable Long id) {
        return new ResponseEntity<Lanche>(service.findById(id), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
