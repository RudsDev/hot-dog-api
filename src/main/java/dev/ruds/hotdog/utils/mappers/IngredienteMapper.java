package dev.ruds.hotdog.utils.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ruds.hotdog.domain.models.Ingrediente;
import dev.ruds.hotdog.domain.records.IngredienteRecord;

@Component
public class IngredienteMapper {
    
    @Autowired
    private ObjectMapper mapper;

    public Ingrediente toEntity(IngredienteRecord record) {
        return mapper.convertValue(record, Ingrediente.class);
    }
}
