package dev.ruds.hotdog.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ruds.hotdog.domain.models.Ingrediente;
import dev.ruds.hotdog.domain.records.IngredienteRecord;
import dev.ruds.hotdog.domain.respositorys.IngredientesRepository;
import dev.ruds.hotdog.utils.mappers.IngredienteMapper;

@Service
public class IngredientesService {
    
    @Autowired
    IngredienteMapper mapper;

    @Autowired
    IngredientesRepository repository;

    public Ingrediente create(IngredienteRecord record) {
        var ingrediente = mapper.toEntity(record);
        return repository.save(ingrediente);
    }

    public List<Ingrediente> findAll() {
        return repository.findAll();
    }
}
