package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.tiposCalculo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AcrescimoPorcentagemTest {

    AcrescimoPorcentagem acrescimo;

    @BeforeEach
    void setup() {
        acrescimo = new AcrescimoPorcentagem();
    }

    @Test
    void calculaPromocao_deveRetornarSomaComAcrescimoDePercentual() {       
        BigDecimal somaItens = BigDecimal.valueOf(100.00);
        Double baseCalculo = 10.0;
        BigDecimal expected = new BigDecimal("110.00");

        BigDecimal result = acrescimo.calculaPromocao(somaItens, baseCalculo);
        assertEquals(expected, result);
    }
    
    @Test
    void deveLancarException_QuandoNenhumBaseCalculoaFornecido() throws Exception {
        Double baseCalculo = null;
        BigDecimal somaItens = BigDecimal.valueOf(100.00);
        String exceptionMsg = "valor_invalido";
        AbstractThrowableAssert<?, ?> exception = Assertions
            .assertThatThrownBy(() -> acrescimo.calculaPromocao(somaItens, baseCalculo))
            .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }

    @Test
    void deveLancarException_QuandoNenhumSomaItensFornecido() throws Exception {
        Double baseCalculo = 10.0;
        BigDecimal somaItens = null;
        String exceptionMsg = "valor_invalido";
        AbstractThrowableAssert<?, ?> exception = Assertions
            .assertThatThrownBy(() -> acrescimo.calculaPromocao(somaItens, baseCalculo))
            .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }

    @Test
    void deveLancarException_QuandoNenhumBaseCalculoaAndSomaItensFornecido() throws Exception {
        Double baseCalculo = null;
        BigDecimal somaItens = null;
        String exceptionMsg = "valor_invalido";
        AbstractThrowableAssert<?, ?> exception = Assertions
            .assertThatThrownBy(() -> acrescimo.calculaPromocao(somaItens, baseCalculo))
            .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }
}
