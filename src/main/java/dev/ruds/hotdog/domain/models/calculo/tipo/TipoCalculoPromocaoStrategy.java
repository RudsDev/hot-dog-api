package dev.ruds.hotdog.domain.models.calculo.tipo;

import java.math.BigDecimal;

public interface TipoCalculoPromocaoStrategy {
    BigDecimal calculaPromocao(BigDecimal somaItens, Double baseCalculo);

    default void validaValores(BigDecimal somaItens, Double baseCalculo) {
        if(baseCalculo == null || somaItens == null)
            throw new RuntimeException("valor_invalido");
    }
}
