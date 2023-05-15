package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao;

import java.math.BigDecimal;

public interface CalculaPromocaoStrategy {
    BigDecimal calculaPromocao(BigDecimal somaItens, Double baseCalculo);
}
