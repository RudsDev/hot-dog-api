package ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class LancheOutput {
    Long id;
    String nome;
    List<IngredienteOutput> ingredientes;
    BigDecimal preco;
}
