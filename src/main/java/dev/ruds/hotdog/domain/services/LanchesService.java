package dev.ruds.hotdog.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ruds.hotdog.domain.dtos.outputs.LanchePartialRecord;
import dev.ruds.hotdog.domain.models.Ingrediente;
import dev.ruds.hotdog.domain.models.Lanche;
import dev.ruds.hotdog.domain.records.LancheRecord;
import dev.ruds.hotdog.domain.respositorys.IngredientesRepository;
import dev.ruds.hotdog.domain.respositorys.LanchesRepository;
import dev.ruds.hotdog.utils.mappers.LancheMapper;

@Service
public class LanchesService {
    
    @Autowired
    LancheMapper mapper;

    @Autowired
    LanchesRepository repository;

    @Autowired
    IngredientesRepository repositoryLanches;

    public Lanche create(LancheRecord record) {
        var ingredientes = ingredientes(record.ingredientes());
        return repository.save(new Lanche(record.nome(), ingredientes));
    }

    public List<LanchePartialRecord> findAll() {
        return repository
            .findAll()
            .stream()
            .map(i -> mapper.toOutputRecord(i))
            .toList();
    }

    public Lanche findById(Long id) {
        var optional = repository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public Lanche update(Long id, LancheRecord record) {
        var ingredientes = ingredientesOnlyId(record.ingredientes());
        var lanche = new Lanche(id, record.nome(), ingredientes);
        return repository.save(lanche);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private List<Ingrediente> ingredientes(List<Long> ingredientes) {
        return repositoryLanches.findAllById(ingredientes);
    }

    private List<Ingrediente> ingredientesOnlyId(List<Long> ingredientes) {
        return ingredientes.stream().map(i -> new Ingrediente(i)).toList();
    }

}
