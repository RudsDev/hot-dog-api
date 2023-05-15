package ruds.desafio.alterdata.servicos.hotdog.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruds.desafio.alterdata.servicos.hotdog.api.v1.assembler.ItemPromocaoMapper;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.ItemPromocaoInput;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.PromocaoInput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Lanche;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.ItemPromocao;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.Promocao;
import ruds.desafio.alterdata.servicos.hotdog.domain.repository.promocao.PromocaoRepository;

@Service
public class PromocoesService {

    @Autowired
    ItemPromocaoMapper itemPromocaoMapper;

    @Autowired
    LancheService lancheService;

    @Autowired
    PromocaoRepository repository;

    public Promocao save(PromocaoInput input) {
        Promocao promocao = createPromocao(input);
        Promocao persisted = repository.save(promocao);
        return persisted;
    }

    public Promocao update(PromocaoInput input) {
        Optional<Promocao> updated = updateLanche(input);
        return updated.isPresent() ? updated.get() : null;
    }

    public Promocao getById(Long id) {
        Optional<Promocao> found = repository.findById(id);
        return found.isPresent()
         ? found.get()
         : null;
    }

    public List<Promocao> getAll() {
        List<Promocao> promocoes = repository.findAll();
        return promocoes;
    }

    public Long delete(Long id) {
        repository.deleteById(id);
        return id;
    }

    private Promocao createPromocao(PromocaoInput input) {
        List<ItemPromocao> itens = filtraPorLanchesExistentes(input.getItens());
        Promocao promocao = new Promocao(input.getId(), input.getNome(), itens, input.getTipoCalculo(), input.getBaseCalculo());
        return promocao;
    }

    private Optional<Promocao> updateLanche(PromocaoInput input) {
        Optional<Promocao> found = repository.findById(input.getId());
        
        if(found.isPresent()) {
            List<ItemPromocao> itens = filtraPorLanchesExistentes(input.getItens());
            Promocao promocao = new Promocao(input.getId(), input.getNome(), itens, input.getTipoCalculo(), input.getBaseCalculo());
            Promocao persisted = repository.save(promocao);
            return Optional.of(persisted);
        }
        return Optional.empty();
    }

    private List<ItemPromocao> filtraPorLanchesExistentes(List<ItemPromocaoInput> itens) {
        Function<ItemPromocaoInput, ItemPromocao> toEntity = (i) -> {
            Lanche lanche = lancheService.getModelById(i.getLanche());
            return itemPromocaoMapper.toEntity(i, lanche);
        };
        List<Long> inputLanchesIds = itens.stream().map((i) -> i.getLanche()).collect(Collectors.toList());
        List<Long> persistedslanchesIds = lancheService.getAllById(inputLanchesIds).stream().map((i) -> i.getId()).collect(Collectors.toList());
        List<ItemPromocaoInput> filtredByPersisteds = itens.stream().filter((i) -> persistedslanchesIds.contains(i.getLanche())).collect(Collectors.toList());
        List<ItemPromocao> filtreds = filtredByPersisteds.stream().map(toEntity).collect(Collectors.toList());
        return filtreds;
    }

}
