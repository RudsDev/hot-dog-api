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

import ruds.desafio.alterdata.servicos.hotdog.api.v1.assembler.PromocaoMapper;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.PromocaoInput;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output.PromocaoOutput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.Promocao;
import ruds.desafio.alterdata.servicos.hotdog.domain.services.PromocoesService;

@RestController
@RequestMapping("/v1/promocoes")
public class PromocoesController {

    @Autowired
    PromocoesService service;

    @Autowired
    PromocaoMapper promocaoMapper;

    @PostMapping()
    public ResponseEntity<PromocaoOutput> save(
        @Valid @RequestBody PromocaoInput input,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        Promocao promocao = service.save(input);
        PromocaoOutput output = promocaoMapper.toOutputDto(promocao);
        return new ResponseEntity<PromocaoOutput>(output, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Promocao> update(
        @Valid @RequestBody PromocaoInput input,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        Promocao promocao = service.update(input);
        return new ResponseEntity<Promocao>(promocao, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocao> getById(
        @Valid @PathVariable Long id,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        Promocao output = service.getById(id);
        return new ResponseEntity<Promocao>(output, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PromocaoOutput>> getAll() {
        List<Promocao> promocaos = service.getAll();
        List<PromocaoOutput> outputs = promocaoMapper.toOutput(promocaos);
        return new ResponseEntity<List<PromocaoOutput>>(outputs, HttpStatus.OK);
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
