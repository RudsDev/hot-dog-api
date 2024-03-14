package dev.ruds.hotdog.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ruds.hotdog.domain.models.ItemPromocao;
import dev.ruds.hotdog.domain.models.Lanche;
import dev.ruds.hotdog.domain.models.Promocao;
import dev.ruds.hotdog.domain.records.ItemPromocaoRecord;
import dev.ruds.hotdog.domain.records.PromocaoOutputRecord;
import dev.ruds.hotdog.domain.records.PromocaoRecord;
import dev.ruds.hotdog.domain.respositorys.LanchesRepository;
import dev.ruds.hotdog.domain.respositorys.PromocoesRepository;
import dev.ruds.hotdog.utils.mappers.IngredienteMapper;
import dev.ruds.hotdog.utils.mappers.PromocaoMapper;

@Service
public class PromocoesService {
    
    @Autowired
    IngredienteMapper mapper;

    @Autowired
    PromocaoMapper promocaoMapper;

    @Autowired
    PromocoesRepository repository;

    @Autowired
    LanchesRepository repositoryLanches;

    public PromocaoOutputRecord create(PromocaoRecord record) {
        var itens = createItensPromocaoNoDbAcess(record.itens());
        var promocao = repository.save(new Promocao(record, itens));
        return promocaoMapper.toRecord(promocao);
    }

    public List<Promocao> findAll() {
        var iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public Promocao findById(Long id) {
        var optional = repository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public Promocao update(Long id, PromocaoRecord record) {
        var itens = createItensPromocao(record.itens());
        var lanche = new Promocao(id, record.nome(), itens, record.tipoCalculo(), record.baseCalculo());
        return repository.save(lanche);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private List<ItemPromocao> createItensPromocao(List<ItemPromocaoRecord> itens) {
        var lanchesId = itens.stream().map(i -> i.lanche()).toList();
        var qtds = itens.stream().map(i -> i.qtd()).toList();
        var lanches = repositoryLanches.findAllById(lanchesId);
        return createItensPromocaoLot(qtds, lanches);
    }

    private List<ItemPromocao> createItensPromocaoNoDbAcess(List<ItemPromocaoRecord> itens) {
        var qtds = itens.stream().map(i -> i.qtd()).toList();
        var lanches = itens.stream().map(i -> new Lanche(i.lanche())).toList();
        return createItensPromocaoLot(qtds, lanches);
    }

    private List<ItemPromocao> createItensPromocaoLot(List<Integer> qtds, List<Lanche> lanches) {
        var itensPromocao = new ArrayList<ItemPromocao>();
        for (int j = 0; j < lanches.size(); j++)
            itensPromocao.add(new ItemPromocao(qtds.get(j), lanches.get(j)));
        return itensPromocao;
    }

}
