package dev.ruds.hotdog.utils.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ruds.hotdog.domain.dtos.outputs.LanchePartialRecord;
import dev.ruds.hotdog.domain.models.Lanche;
import dev.ruds.hotdog.domain.records.LancheRecord;

@Component
public class LancheMapper {
    
    @Autowired
    private ObjectMapper mapper;

    public Lanche toEntity(LancheRecord record) {
        return mapper.convertValue(record, Lanche.class);
    }

    public LanchePartialRecord toOutputRecord(Lanche lanche) {
        String preco = lanche.preco().toString();
        return new LanchePartialRecord(lanche.getId(), preco, lanche.getNome());
    }
}
