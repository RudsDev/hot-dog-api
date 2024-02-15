package dev.ruds.hotdog.api.v1.promocao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ruds.hotdog.domain.models.Lanche;
import dev.ruds.hotdog.domain.models.Promocao;
import dev.ruds.hotdog.domain.models.calculo.tipo.TipoCalculoPromocao;
import dev.ruds.hotdog.domain.records.IngredienteRecord;
import dev.ruds.hotdog.domain.services.LanchesService;

@RestController
@RequestMapping("/v1/promocoes")
public class PromocaoController {

    @Autowired
    LanchesService service;

    @PostMapping()
    public String create(
        @RequestBody IngredienteRecord input
    ) {
        var promocao = new Promocao("Teste", new ArrayList<Lanche>(), TipoCalculoPromocao.NAO_ALTERA, 50.0);
        return promocao.preco().toString();
    }
}