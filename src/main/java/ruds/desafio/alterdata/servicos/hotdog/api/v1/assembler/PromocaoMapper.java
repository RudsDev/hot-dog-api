package ruds.desafio.alterdata.servicos.hotdog.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.PromocaoInput;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output.PromocaoOutput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.Promocao;

@Component
public class PromocaoMapper {

    private ObjectMapper mapper = new ObjectMapper();

    public Promocao toEntity(PromocaoInput input) {
        Promocao promocao = entity(input);
        return promocao;
    }

    public PromocaoInput toInputDto(Promocao entity) {
        PromocaoInput input = input(entity);
        return input;
    }

    public PromocaoOutput toOutputDto(Promocao entity) {
        PromocaoOutput output = output(entity);        
        output.setPreco(entity.calculaPreco());
        return output;
    }

    public List<PromocaoOutput> toOutput(List<Promocao> entitys) {
        return entitys.stream().map(this::output).collect(Collectors.toList());
    }

    private PromocaoOutput output(Promocao target) {
        Class<PromocaoOutput> clazz = PromocaoOutput.class;
        PromocaoOutput output = mapper.convertValue(target, clazz);
        output.setPreco(target.calculaPreco());
        return output;
    }

    private PromocaoInput input(Promocao target) {
        Class<PromocaoInput> clazz = PromocaoInput.class;
        return mapper.convertValue(target, clazz);
    }

    private Promocao entity(PromocaoInput target) {
        PromocaoInput i = target;
        return new Promocao(i.getId(), i.getNome(), i.getTipoCalculo(), i.getBaseCalculo());
    }
}
