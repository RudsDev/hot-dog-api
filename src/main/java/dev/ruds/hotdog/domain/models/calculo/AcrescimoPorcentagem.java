package dev.ruds.hotdog.domain.models.calculo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AcrescimoPorcentagem implements CalculaPromocaoStrategy {

    @Override
    public BigDecimal calculaPromocao(BigDecimal somaItens, Double baseCalculo) {
        validaValores(somaItens, baseCalculo);
        BigDecimal percent = BigDecimal.valueOf(baseCalculo / 100.0);
        BigDecimal add = percent.multiply(somaItens);
        return somaItens.add(add).setScale(2, RoundingMode.UP);
    }
    
}
