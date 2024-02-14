package dev.ruds.hotdog.domain.models.calculo.tipo;

import java.math.BigDecimal;
import java.math.RoundingMode;

class AcrescimoPorcentagem implements TipoCalculoPromocaoStrategy {

    @Override
    public BigDecimal calculaPromocao(BigDecimal somaItens, Double baseCalculo) {
        validaValores(somaItens, baseCalculo);
        BigDecimal percent = BigDecimal.valueOf(baseCalculo / 100.0);
        BigDecimal add = percent.multiply(somaItens);
        return somaItens.add(add).setScale(2, RoundingMode.UP);
    }
    
}
