package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.tiposCalculo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
    AcrescimoValor.class
})
public class AcrescimoValorTest {

    AcrescimoValor acrescimo;

    @BeforeEach
    void setup() {
        acrescimo = new AcrescimoValor();
    }

    @Test
    void testCalculaPromocaoComValoresNegativos() {
        BigDecimal somaItens = new BigDecimal("-15.30");
        BigDecimal resultadoEsperado = new BigDecimal("-18.80");
        Double baseCalculo = -3.50;

        BigDecimal resultado = acrescimo.calculaPromocao(somaItens, baseCalculo);

        assertEquals(resultadoEsperado, resultado);
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
