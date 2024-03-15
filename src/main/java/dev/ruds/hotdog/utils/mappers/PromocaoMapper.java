package dev.ruds.hotdog.utils.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ruds.hotdog.domain.models.Promocao;
import dev.ruds.hotdog.domain.records.PromocaoOutputRecord;

@Component
public class PromocaoMapper {
    
    @Autowired
    private ObjectMapper mapper;

    public PromocaoOutputRecord toRecord(Promocao promocao) {
        return mapper.convertValue(promocao, PromocaoOutputRecord.class);
    }
}
