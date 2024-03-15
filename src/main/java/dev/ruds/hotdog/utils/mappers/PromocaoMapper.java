package dev.ruds.hotdog.utils.mappers;

import org.springframework.stereotype.Component;

import dev.ruds.hotdog.domain.models.Promocao;
import dev.ruds.hotdog.domain.records.PromocaoOutputRecord;

@Component
public class PromocaoMapper {

    public PromocaoOutputRecord toRecord(Promocao promocao) {
        return new PromocaoOutputRecord(promocao.getId(), promocao.getNome(), 
        promocao.getBaseCalculo(), promocao.getTipoCalculo());
    }
}
