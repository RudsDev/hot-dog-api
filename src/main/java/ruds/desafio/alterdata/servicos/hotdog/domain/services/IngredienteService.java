package ruds.desafio.alterdata.servicos.hotdog.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruds.desafio.alterdata.servicos.hotdog.api.v1.assembler.IngredienteMapper;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.IngredienteInput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Ingrediente;
import ruds.desafio.alterdata.servicos.hotdog.domain.repository.IngredienteRepository;

@Service
public class IngredienteService {
    
    @Autowired
    IngredienteMapper mapper;

    @Autowired
    IngredienteRepository repository;

    public Ingrediente save(IngredienteInput input) {
        Ingrediente ingrediente = mapper.toEntity(input);
        System.out.println(ingrediente);
        Ingrediente persisted = repository.save(ingrediente);
        return persisted;
    }

    public Ingrediente update(IngredienteInput input) {
        Ingrediente source = mapper.toEntity(input);
        Ingrediente updated = repository.save(source);
        return updated;
    }

    public Ingrediente getById(Long id) {
        Optional<Ingrediente> updated = repository.findById(id);
        return updated.isPresent()
         ? updated.get()
         : null;
    }

    public List<Ingrediente> getAll() {
        List<Ingrediente> ingredientes = repository.findAll();
        return ingredientes;
    }

    public Long delete(Long id) {
        repository.deleteById(id);
        return id;
    }

    public List<Ingrediente> getAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
