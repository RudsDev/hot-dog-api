package ruds.desafio.alterdata.servicos.hotdog.api.v1.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input.IngredienteInput;
import ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output.IngredienteOutput;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Ingrediente;

@Component
public class IngredienteMapper {

    @Autowired
    private ObjectMapper mapper;


    public Ingrediente toEntity(IngredienteInput input) {
        Ingrediente ingrediente = mapper.convertValue(input, Ingrediente.class);
        return ingrediente;
    }

    public IngredienteInput toInput(Ingrediente entity) {
        IngredienteInput input = mapper.convertValue(entity, IngredienteInput.class);
        return input;
    }

    public IngredienteOutput toOutput(Ingrediente entity) {
        IngredienteOutput output = mapper.convertValue(entity, IngredienteOutput.class);
        return output;
    }
}
