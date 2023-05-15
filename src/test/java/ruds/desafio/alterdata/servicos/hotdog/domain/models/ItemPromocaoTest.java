package ruds.desafio.alterdata.servicos.hotdog.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.ItemPromocao;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
    ItemPromocao.class
})
public class ItemPromocaoTest {
    
    
    @Test
    void deveRetornarPrecoCorreto() {
        Ingrediente pao = new Ingrediente("Pão", new BigDecimal("2.00"));
        Ingrediente linguica = new Ingrediente("Linguiça", new BigDecimal("3.00"));
        Long quantidade = 2L;
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(pao);
        ingredientes.add(linguica);
        Lanche lanche = new Lanche(1L, "Lanche", ingredientes);
        ItemPromocao item = new ItemPromocao(quantidade, lanche);

        BigDecimal expected = new BigDecimal("10.00");
        BigDecimal actual = item.calculaPreco();

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarZero_QuandoPrecosZero() {
        Ingrediente pao = new Ingrediente("Pão", BigDecimal.ZERO);
        Ingrediente linguica = new Ingrediente("Linguiça", BigDecimal.ZERO);
        Long quantidade = 2L;

        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(pao);
        ingredientes.add(linguica);
        Lanche lanche = new Lanche(1L, "Lanche", ingredientes);
        ItemPromocao item = new ItemPromocao(quantidade, lanche);

        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = item.calculaPreco();

        assertEquals(expected, actual);
    }

    @Test
    void deveRetornarZero_QuandoQuantidadeZero() {
        Ingrediente pao = new Ingrediente("Pão", BigDecimal.ZERO);
        Ingrediente linguica = new Ingrediente("Linguiça", BigDecimal.ZERO);
        Long quantidade = 0L;

        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(pao);
        ingredientes.add(linguica);
        Lanche lanche = new Lanche(1L, "Lanche", ingredientes);
        ItemPromocao item = new ItemPromocao(quantidade, lanche);

        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = item.calculaPreco();

        assertEquals(expected, actual);
    }

}
