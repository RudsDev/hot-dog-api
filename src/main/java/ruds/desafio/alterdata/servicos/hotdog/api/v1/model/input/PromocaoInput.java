package ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromocaoInput {
    
    Long id;

    @NotEmpty(message = "{not_blank}")
    @NotBlank(message = "{not_blank}")
    String nome;

    Integer tipoCalculo;
    
    Double baseCalculo;

    @NotEmpty(message = "{not_blank}")
    List<ItemPromocaoInput> itens;


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", tipoCalculo='" + getTipoCalculo() + "'" +
            ", baseCalculo='" + getBaseCalculo() + "'" +
            ", itens='" + getItens() + "'" +
            "}";
    }

}
