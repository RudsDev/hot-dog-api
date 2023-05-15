package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.tiposCalculo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ruds.desafio.alterdata.servicos.hotdog.core.internationalization.Translator;
import ruds.desafio.alterdata.servicos.hotdog.domain.exception.NegocioException;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.CalculaPromocaoStrategy;

public class DescontoPorcentagem implements CalculaPromocaoStrategy {

    @Override
    public BigDecimal calculaPromocao(BigDecimal somaItens, Double baseCalculo) {
        if(baseCalculo == null || somaItens == null)
            throw new NegocioException(Translator.toLocale("valor_invalido"));
        BigDecimal percent = BigDecimal.valueOf(baseCalculo / 100.0);
        BigDecimal add = percent.multiply(somaItens);
        return somaItens.subtract(add).setScale(2, RoundingMode.UP);
    }
    
}
