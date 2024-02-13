package dev.ruds.hotdog.domain.models.calculo;

import java.math.BigDecimal;

public class CalculoPromocao {
    private Double baseCalculo;
    private TipoCalculoPromocao tipoCalculo;
    private CalculaPromocaoStrategy calculo;
    private BigDecimal value;

    public CalculoPromocao() {}

    public CalculoPromocao(Integer tipoCalculo, Double baseCalculo, BigDecimal value) {
        this.tipoCalculo = TipoCalculoPromocao.get(tipoCalculo);
        this.baseCalculo = baseCalculo;
        this.calculo = estrategiaCalculo();
        this.value = value;
    }

    public CalculoPromocao(TipoCalculoPromocao tipoCalculo, Double baseCalculo, BigDecimal value) {
        this.tipoCalculo = tipoCalculo;
        this.baseCalculo = baseCalculo;
        this.calculo = estrategiaCalculo();
        this.value = value;
    }

    public BigDecimal calculaPromocao() {
        return calculo.calculaPromocao(value, baseCalculo);
    }

    private CalculaPromocaoStrategy estrategiaCalculo() {
        switch (tipoCalculo) {
            case ACRESCIMO_VALOR:
                return new AcrescimoValor();
            case ACRESCIMO_PORCENTAGEM:
                return new AcrescimoPorcentagem();
            case DESCONTO_VALOR:
                return new DescontoValor();
            case DESCONTO_PORCENTAGEM:
                return new DescontoPorcentagem();
            default:
                return null;
        }
    }
}
