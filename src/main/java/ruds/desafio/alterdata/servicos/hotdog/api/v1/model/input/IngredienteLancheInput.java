package ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredienteLancheInput {
    
    @NotEmpty(message = "{not_blank}")
    Long id;


    @NotEmpty(message = "{not_blank}")
    Long qtd;
}
