package ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredienteInput {
    
    Long id;

    @NotEmpty(message = "{not_blank}")
    @NotBlank(message = "{not_blank}")
    String nome;

    @NotEmpty(message = "{not_blank}")
    @NotBlank(message = "{not_blank}")
    String preco;
}
