package ruds.desafio.alterdata.servicos.hotdog.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.ItemPromocaoInput;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output.ItemPromocaoOutput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Lanche;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.ItemPromocao;

@Component
public class ItemPromocaoMapper {
    
    @Autowired
    private ObjectMapper mapper;

    public ItemPromocao toEntity(ItemPromocaoInput input) {
        return new ItemPromocao(input.getId(), input.getQuantidade());    
    }

    public ItemPromocao toEntity(ItemPromocaoInput input, Lanche lanche) {
        return new ItemPromocao(input.getId(), input.getQuantidade(), lanche);    
    }

    public ItemPromocaoOutput toOutput(ItemPromocao entity) {
        ItemPromocaoOutput output = convert(entity);
        return output;
    }

    public List<ItemPromocaoOutput> toOutput(List<ItemPromocao> entitys) {
        return entitys.stream().map(this::convert).collect(Collectors.toList());
    }
    
    private ItemPromocaoOutput convert(ItemPromocao target) {
        Class<ItemPromocaoOutput> clazz = ItemPromocaoOutput.class;
        return mapper.convertValue(target, clazz);
    }

}
