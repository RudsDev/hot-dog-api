package dev.ruds.hotdog.api.v1.promocao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ruds.hotdog.domain.models.Promocao;
import dev.ruds.hotdog.domain.records.PromocaoOutputRecord;
import dev.ruds.hotdog.domain.records.PromocaoRecord;
import dev.ruds.hotdog.domain.services.PromocoesService;

@RestController
@RequestMapping("/v1/promocoes")
public class PromocaoController {

    @Autowired
    PromocoesService service;

    @PostMapping()
    public ResponseEntity<PromocaoOutputRecord> create(
        @RequestBody PromocaoRecord input
    ) {
        var promocao = service.create(input);
        return new ResponseEntity<PromocaoOutputRecord>(promocao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocao> update(
        @PathVariable Long id,
        @RequestBody PromocaoRecord input
    ) {
        var promocao = service.update(id, input);
        return new ResponseEntity<Promocao>(promocao, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PromocaoOutputRecord>> findAll() {
        var list = service.findAll();
        return new ResponseEntity<List<PromocaoOutputRecord>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocao> findById(@PathVariable Long id) {
        return new ResponseEntity<Promocao>(service.findById(id), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}