package dev.ruds.hotdog.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ruds.hotdog.domain.models.Lanche;
import dev.ruds.hotdog.domain.models.Promocao;
import dev.ruds.hotdog.domain.records.PromocaoRecord;
import dev.ruds.hotdog.domain.respositorys.LanchesRepository;
import dev.ruds.hotdog.domain.respositorys.PromocoesRepository;
import dev.ruds.hotdog.utils.mappers.IngredienteMapper;

@Service
public class PromocoesService {
    
    @Autowired
    IngredienteMapper mapper;

    @Autowired
    PromocoesRepository repository;

    @Autowired
    LanchesRepository repositoryLanches;

    public Promocao create(PromocaoRecord record) {
        var itens = lanches(record.itens());
        var promocao = repository.save(new Promocao(record, itens));
        var optional = repository.findById(promocao.getId());
        return optional.isPresent() ? optional.get() : null;
    }

    public List<Promocao> findAll() {
        var list = repository.findAll();
        return list;
    }

    public Promocao findById(Long id) {
        var optional = repository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public Promocao update(Long id, PromocaoRecord record) {
        var lanches = lanchesOnlyId(record.itens());
        var lanche = new Promocao(id, record.nome(), lanches, record.tipoCalculo(), record.baseCalculo());
        return repository.save(lanche);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private List<Lanche> lanches(List<Long> itens) {
        return repositoryLanches.findAllById(itens);
    }

    private List<Lanche> lanchesOnlyId(List<Long> ingredientes) {
        return ingredientes.stream().map(i -> new Lanche(i)).toList();
    }

}
