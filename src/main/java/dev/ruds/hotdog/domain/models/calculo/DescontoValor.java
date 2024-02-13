package dev.ruds.hotdog.domain.models.calculo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescontoValor implements CalculaPromocaoStrategy {

    @Override
    public BigDecimal calculaPromocao(BigDecimal somaItens, Double baseCalculo) {
        validaValores(somaItens, baseCalculo);
        return somaItens.subtract(BigDecimal.valueOf(baseCalculo)).setScale(2, RoundingMode.UP);
    }
    
}
