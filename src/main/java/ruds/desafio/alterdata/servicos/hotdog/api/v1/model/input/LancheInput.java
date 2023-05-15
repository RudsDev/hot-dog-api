package ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancheInput {
    
    Long id;

    @NotEmpty(message = "{not_blank}")
    @NotBlank(message = "{not_blank}")
    String nome;

    @NotEmpty(message = "{not_blank}")
    List<Long> ingredientes;


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", ingredientes='" + getIngredientes() + "'" +
            "}";
    }

}
