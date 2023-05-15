package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao;

import java.math.BigDecimal;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.tiposCalculo.AcrescimoPorcentagem;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.tiposCalculo.AcrescimoValor;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.tiposCalculo.DescontoPorcentagem;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.tiposCalculo.DescontoValor;

public class CalculoPromocao {
    private Double baseCalculo;
    private TipoCalculoPromocao tipoCalculo;
    private CalculaPromocaoStrategy calculo;

    public CalculoPromocao() {}

    public CalculoPromocao(Integer tipoCalculo, Double baseCalculo) {
        this.tipoCalculo = TipoCalculoPromocao.get(tipoCalculo);
        this.baseCalculo = baseCalculo;
        this.calculo = estrategiaCalculo();
    }

    public CalculoPromocao(TipoCalculoPromocao tipoCalculo, Double baseCalculo) {
        this.tipoCalculo = tipoCalculo;
        this.baseCalculo = baseCalculo;
        this.calculo = estrategiaCalculo();
    }

    public BigDecimal calculaPromocao(BigDecimal somaItens) {
        return calculo.calculaPromocao(somaItens, baseCalculo);
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
