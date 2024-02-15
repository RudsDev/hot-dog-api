package dev.ruds.hotdog.domain.models.calculo.tipo;

import java.math.BigDecimal;

class NaoAltera implements TipoCalculoPromocaoStrategy {

    @Override
    public BigDecimal calculaPromocao(BigDecimal somaItens, Double baseCalculo) {
        return somaItens;
    }
    
}
