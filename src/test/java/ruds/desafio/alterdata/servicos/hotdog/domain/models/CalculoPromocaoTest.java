package ruds.desafio.alterdata.servicos.hotdog.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.CalculoPromocao;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.TipoCalculoPromocao;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
    CalculoPromocao.class
})
public class CalculoPromocaoTest {

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoAcrescimoValorEnum() {
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.ACRESCIMO_VALOR;
        Double baseCalculo = 5.0;
        BigDecimal somaItens = BigDecimal.valueOf(10);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("15.00");
        BigDecimal actual = calculo.calculaPromocao(somaItens);

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoAcrescimoValorInteger() {
        Integer tipoCalculo = TipoCalculoPromocao.ACRESCIMO_VALOR.getTipo();
        Double baseCalculo = 5.0;
        BigDecimal somaItens = BigDecimal.valueOf(10);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("15.00");
        BigDecimal actual = calculo.calculaPromocao(somaItens);

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoAcrescimoPorcentagemEnum() {
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.ACRESCIMO_PORCENTAGEM;
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("110.00");
        BigDecimal actual = calculo.calculaPromocao(somaItens);

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoAcrescimoPorcentagemInteger() {
        Integer tipoCalculo = TipoCalculoPromocao.ACRESCIMO_PORCENTAGEM.getTipo();
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("110.00");
        BigDecimal actual = calculo.calculaPromocao(somaItens);

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoDescontoValorEnum() {
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.DESCONTO_VALOR;
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal actual = calculo.calculaPromocao(somaItens);

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoDescontoValorInteger() {
        Integer tipoCalculo = TipoCalculoPromocao.DESCONTO_VALOR.getTipo();
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal actual = calculo.calculaPromocao(somaItens);

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoDescontoPorcentagemEnum() {
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.DESCONTO_PORCENTAGEM;
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal actual = calculo.calculaPromocao(somaItens);

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoDescontoPorcentagemInteger() {
        Integer tipoCalculo = TipoCalculoPromocao.DESCONTO_PORCENTAGEM.getTipo();
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal actual = calculo.calculaPromocao(somaItens);

        assertEquals(expected, actual);
    }

    @Test
    void deveLancarException_QuandoTipoCalculoDesconhecido() throws Exception {
        String exceptionMsg = "tipo_promocao_desconhecido";
        Integer tipoCalculo = Integer.MIN_VALUE;
        Double baseCalculo = 10.0;

        AbstractThrowableAssert<?, ?> exception = Assertions
            .assertThatThrownBy(() -> new CalculoPromocao(tipoCalculo, baseCalculo))
            .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }
}
