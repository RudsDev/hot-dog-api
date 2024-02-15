package dev.ruds.hotdog.domain.models.calculo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.ruds.hotdog.domain.models.calculo.tipo.TipoCalculoPromocao;

// @SpringBootTest
public class CalculoPromocaoTest {

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoAcrescimoValorEnum() {
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.ACRESCIMO_VALOR;
        Double baseCalculo = 5.0;
        BigDecimal somaItens = BigDecimal.valueOf(10);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = new BigDecimal("15.00");
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

@Test
    void deveRetornarValorCorreto_QuandoTipoCalculoAcrescimoValorInteger() {
        Integer tipoCalculo = TipoCalculoPromocao.ACRESCIMO_VALOR.getTipo();
        Double baseCalculo = 5.0;
        BigDecimal somaItens = BigDecimal.valueOf(10);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = new BigDecimal("15.00");
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoAcrescimoPorcentagemEnum() {
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.ACRESCIMO_PORCENTAGEM;
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = new BigDecimal("110.00");
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoAcrescimoPorcentagemInteger() {
        Integer tipoCalculo = TipoCalculoPromocao.ACRESCIMO_PORCENTAGEM.getTipo();
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = new BigDecimal("110.00");
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoDescontoValorEnum() {
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.DESCONTO_VALOR;
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoDescontoValorInteger() {
        Integer tipoCalculo = TipoCalculoPromocao.DESCONTO_VALOR.getTipo();
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoDescontoPorcentagemEnum() {
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.DESCONTO_PORCENTAGEM;
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarValorCorreto_QuandoTipoCalculoDescontoPorcentagemInteger() {
        Integer tipoCalculo = TipoCalculoPromocao.DESCONTO_PORCENTAGEM.getTipo();
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

    @Test
    void deveLancarException_QuandoBaseCalculoNull() {
        String exceptionMsg = "valor_invalido";
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.DESCONTO_PORCENTAGEM;
        Double baseCalculo = null;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        AbstractThrowableAssert<?, ?> exception = Assertions
        .assertThatThrownBy(() -> calculo.calculaPromocao())
        .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }

    @Test
    void deveRetornarMesmoValor_QuandoTipoCalculoDesconhecidoPelaFactory() {
        Integer tipoCalculo = TipoCalculoPromocao.NAO_ALTERA.getTipo();
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.valueOf(100);
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        BigDecimal expected = somaItens;
        BigDecimal actual = calculo.calculaPromocao();

        assertEquals(expected, actual);
    }

    @Test
    void deveLancarException_QuandoSomaItensNull() {
        String exceptionMsg = "valor_invalido";
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.DESCONTO_PORCENTAGEM;
        Double baseCalculo = 10.0;
        BigDecimal somaItens = null;
        CalculoPromocao calculo = new CalculoPromocao(tipoCalculo, baseCalculo, somaItens);

        AbstractThrowableAssert<?, ?> exception = Assertions
        .assertThatThrownBy(() -> calculo.calculaPromocao())
        .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }

    @Test
    void deveLancarException_QuandoTipoCalculoDesconhecido() throws Exception {
        String exceptionMsg = "tipo_promocao_desconhecido";
        Integer tipoCalculo = Integer.MIN_VALUE;
        Double baseCalculo = 10.0;
        BigDecimal somaItens = BigDecimal.ZERO;

        AbstractThrowableAssert<?, ?> exception = Assertions
            .assertThatThrownBy(() -> new CalculoPromocao(tipoCalculo, baseCalculo, somaItens))
            .hasNoCause();
        exception.hasMessageContaining(exceptionMsg);
    }
}
