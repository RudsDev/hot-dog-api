package dev.ruds.hotdog.domain.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ruds.hotdog.domain.dtos.inputs.IngredientePrecoPartialRecord;
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

    public Ingrediente findById(Long id){
        var optional = repository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public Ingrediente update(Long id, IngredienteRecord record) {
        var ingrediente = mapper.toEntity(id, record);
        return repository.save(ingrediente);
    }

    public void updatePreco(IngredientePrecoPartialRecord record) {
        repository.updatePreco(record.id(), new BigDecimal(record.preco()));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
