package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.tiposCalculo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DescontoValorTest {
    
    DescontoValor desconto;

    @BeforeEach
    void setup() {
        desconto = new DescontoValor();
    }

    @Test
    void testCalculaPromocaoComValoresNegativos() {
        BigDecimal somaItens = new BigDecimal("-5");
        BigDecimal resultadoEsperado = new BigDecimal("-1.00");
        Double baseCalculo = -4.00;

        BigDecimal resultado = desconto.calculaPromocao(somaItens, baseCalculo);

        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    void deveLancarException_QuandoNenhumBaseCalculoaFornecido() throws Exception {
        Double baseCalculo = null;
        BigDecimal somaItens = BigDecimal.valueOf(100.00);
        String exceptionMsg = "valor_invalido";
        AbstractThrowableAssert<?, ?> exception = Assertions
            .assertThatThrownBy(() -> desconto.calculaPromocao(somaItens, baseCalculo))
            .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }

    @Test
    void deveLancarException_QuandoNenhumSomaItensFornecido() throws Exception {
        Double baseCalculo = 10.0;
        BigDecimal somaItens = null;
        String exceptionMsg = "valor_invalido";
        AbstractThrowableAssert<?, ?> exception = Assertions
            .assertThatThrownBy(() -> desconto.calculaPromocao(somaItens, baseCalculo))
            .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }

    @Test
    void deveLancarException_QuandoNenhumBaseCalculoaAndSomaItensFornecido() throws Exception {
        Double baseCalculo = null;
        BigDecimal somaItens = null;
        String exceptionMsg = "valor_invalido";
        AbstractThrowableAssert<?, ?> exception = Assertions
            .assertThatThrownBy(() -> desconto.calculaPromocao(somaItens, baseCalculo))
            .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }

}
