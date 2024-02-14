package dev.ruds.hotdog.domain.models.calculo.tipo;

import java.math.BigDecimal;
import java.math.RoundingMode;

class DescontoValor implements TipoCalculoPromocaoStrategy {

    @Override
    public BigDecimal calculaPromocao(BigDecimal somaItens, Double baseCalculo) {
        validaValores(somaItens, baseCalculo);
        return somaItens.subtract(BigDecimal.valueOf(baseCalculo)).setScale(2, RoundingMode.UP);
    }
    
}
