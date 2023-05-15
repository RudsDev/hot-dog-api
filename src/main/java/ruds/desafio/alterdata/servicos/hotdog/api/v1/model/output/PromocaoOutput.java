package ruds.desafio.alterdata.servicos.hotdog.api.v1.model.output;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PromocaoOutput {
    Long id;
    String nome;
    Integer tipoCalculo;
    Double baseCalculo;
    BigDecimal preco;
    List<ItemPromocaoOutput> itens;
}
