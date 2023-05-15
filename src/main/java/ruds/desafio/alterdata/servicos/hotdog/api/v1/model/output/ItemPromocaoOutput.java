package ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPromocaoOutput {
    Long id;
    Long quantidade;
    LancheOutput lanche;
}
