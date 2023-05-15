package ruds.desafio.alterdata.servicos.hotdog.domain.models.calculos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.Lanche;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
    Lanche.class
})
public class CalculavelTest {

    @Autowired
    Lanche calculavel;
    

    @Test
    void sumComListaVazia() {
        Stream<BigDecimal> emptyStream = Stream.empty();
        BigDecimal expectedSum = BigDecimal.valueOf(0);
        
        BigDecimal actualSum = calculavel.sum(emptyStream);

        assertEquals(expectedSum, actualSum);
    }

    @Test
    void sumComListahUmElemento() {
        BigDecimal value = BigDecimal.valueOf(99);
        Stream<BigDecimal> emptyStream = Stream.of(value);
        BigDecimal expectedSum = value;

        BigDecimal actualSum = calculavel.sum(emptyStream);

        assertEquals(expectedSum, actualSum);
    }

    @Test
    void sumComListaVariosElementos() {
        BigDecimal one = BigDecimal.valueOf(1);
        BigDecimal two = BigDecimal.valueOf(2);
        BigDecimal three = BigDecimal.valueOf(3);
        Stream<BigDecimal> emptyStream = Stream.of(one, two, three);
        BigDecimal expectedSum = BigDecimal.valueOf(6);

        BigDecimal actualSum = calculavel.sum(emptyStream);

        assertEquals(expectedSum, actualSum);
    }

    @Test
    void sumComListaValoresNegativos() {
        
        BigDecimal valueOne = BigDecimal.valueOf(-1.5);
        BigDecimal valueTwo = BigDecimal.valueOf(-2.5);
        BigDecimal valueThree = BigDecimal.valueOf(-3.0);

        Stream<BigDecimal> numberStream = Stream.of(valueOne, valueTwo, valueThree);
        BigDecimal expectedSum = BigDecimal.valueOf(-7.0);
        BigDecimal actualSum = calculavel.sum(numberStream);

        assertEquals(expectedSum, actualSum);
    }
}
