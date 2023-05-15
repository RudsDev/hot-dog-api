package ruds.desafio.alterdata.servicos.hotdog.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPromocaoInput {
    Long id;
    Long quantidade;
    Long lanche;

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " lanche='" + getLanche() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            "}";
    }
}
