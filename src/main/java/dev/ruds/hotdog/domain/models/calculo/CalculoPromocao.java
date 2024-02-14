package dev.ruds.hotdog.domain.models.calculo;

import java.math.BigDecimal;

import dev.ruds.hotdog.domain.models.calculo.tipo.TipoCalculoPromocao;
import dev.ruds.hotdog.domain.models.calculo.tipo.TipoCalculoPromocaoFactory;

public class CalculoPromocao {
    private Double baseCalculo;
    private TipoCalculoPromocao tipoCalculo;
    private TipoCalculoPromocaoFactory tipoCalculoFactory;
    private BigDecimal value;

    public CalculoPromocao() {}

    public CalculoPromocao(Integer tipoCalculo, Double baseCalculo, BigDecimal value) {
        this.tipoCalculo = TipoCalculoPromocao.get(tipoCalculo);
        this.baseCalculo = baseCalculo;
        this.value = value;
    }

    public CalculoPromocao(TipoCalculoPromocao tipoCalculo, Double baseCalculo, BigDecimal value) {
        this.tipoCalculo = tipoCalculo;
        this.baseCalculo = baseCalculo;
        this.value = value;
    }

    public BigDecimal calculaPromocao() {
        return tipoCalculoFactory
            .create(tipoCalculo)
            .calculaPromocao(value, baseCalculo);
    }
}
