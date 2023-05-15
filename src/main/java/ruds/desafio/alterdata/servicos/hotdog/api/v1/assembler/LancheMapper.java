package ruds.desafio.alterdata.servicos.hotdog.api.v1.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.LancheInput;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output.LancheOutput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Lanche;

@Component
public class LancheMapper {

    @Autowired
    private ObjectMapper mapper;


    public Lanche toEntity(LancheInput input) {
        Lanche lanche = new Lanche(input.getId(), input.getNome());
        return lanche;
    }

    public LancheInput toInputDto(Lanche entity) {
        LancheInput input = mapper.convertValue(entity, LancheInput.class);
        return input;
    }

    public LancheOutput toOutputDto(Lanche entity) {
        LancheOutput output = mapper.convertValue(entity, LancheOutput.class);
        output.setPreco(entity.calculaPreco());
        return output;
    }
}
