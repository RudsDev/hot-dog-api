package ruds.desafio.alterdata.servicos.hotdog.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruds.desafio.alterdata.servicos.hotdog.api.v1.assembler.LancheMapper;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.LancheInput;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output.LancheOutput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Ingrediente;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Lanche;
import ruds.desafio.alterdata.servicos.hotdog.domain.repository.lanche.LancheRepository;

@Service
public class LancheService {

    @Autowired
    LancheMapper mapper;

    @Autowired
    IngredienteService ingredienteService;

    @Autowired
    LancheRepository repository;

    public Lanche save(LancheInput input) {
        Lanche lanche = createLanche(input);
        Lanche persisted = repository.save(lanche);
        return persisted;
    }

    public LancheOutput update(LancheInput input) {
        Optional<Lanche> updated = updateLanche(input);
        return updated.isPresent() ? mapper.toOutputDto(updated.get()) : null;
    }

    public LancheOutput getById(Long id) {
        Optional<Lanche> found = repository.findById(id);
        return found.isPresent()
         ? mapper.toOutputDto(found.get())
         : null;
    }

    public Lanche getModelById(Long id) {
        Optional<Lanche> found = repository.findById(id);
        return found.isPresent()
         ? found.get()
         : null;
    }

    public List<LancheOutput> getAll() {
        List<Lanche> lanches = repository.findAll();
        List<LancheOutput> outputs = lanches.stream().map(mapper::toOutputDto).collect(Collectors.toList());
        return outputs;
    }

    public List<Lanche> getAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public Long delete(Long id) {
        repository.deleteById(id);
        return id;
    }

    private Lanche createLanche(LancheInput input) { 
        Lanche lanche = new Lanche(input.getId(), input.getNome());
        List<Ingrediente> ingredientes = ingredientes(input);
        lanche.addIngredientes(ingredientes);
        return lanche;
    }

    private Optional<Lanche> updateLanche(LancheInput input) {
        Optional<Lanche> found = repository.findById(input.getId());

        if(found.isPresent()) {
            Lanche lanche = new Lanche(input.getId(), input.getNome(), ingredientes(input));
            Lanche persisted = repository.save(lanche);
            return Optional.of(persisted);
        }
        return Optional.empty();
    }
    
    private List<Ingrediente> ingredientes(LancheInput input) {
        List<Long> ingredientesIds = input.getIngredientes();
        //input.getIngredientes().stream().map(i -> i.getId()).collect(Collectors.toList());
        List<Ingrediente> ingredientes = ingredienteService.getAllById(ingredientesIds);
        return ingredientes;
    }
    
}
