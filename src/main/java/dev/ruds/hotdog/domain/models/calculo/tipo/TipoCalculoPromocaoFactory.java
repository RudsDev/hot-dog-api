package dev.ruds.hotdog.domain.models.calculo.tipo;

public class TipoCalculoPromocaoFactory {
    public TipoCalculoPromocaoStrategy create(TipoCalculoPromocao tipoCalculo) {
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
