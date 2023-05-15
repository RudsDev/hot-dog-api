package ruds.desafio.alterdata.servicos.hotdog.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.ItemPromocao;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.Promocao;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.TipoCalculoPromocao;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
    Promocao.class
})
public class PromocaoTest {
    
    Promocao promocao;

    List<ItemPromocao> itens = new ArrayList<>();

    Ingrediente pao = new Ingrediente("Pão", new BigDecimal("2.00"));
    Ingrediente batataPalha = new Ingrediente("Batata palha", new BigDecimal("0.40"));
    Ingrediente linguica = new Ingrediente("Linguiça", new BigDecimal("3.00"));


    @BeforeEach
    void setup() {
        promocao = new Promocao();
    }

    @Test
    void deveCalcularPrecoCorretamente() {
        String nome = "Promoção";
        Double baseCalculo = 5.0;
        TipoCalculoPromocao tipoCalculo = TipoCalculoPromocao.ACRESCIMO_VALOR;
        List<Ingrediente> ingredientes = new ArrayList<>();
        Lanche classico = new Lanche(1L, "Clássico", ingredientes);
        ItemPromocao item = new ItemPromocao(2L, classico);

        
        ingredientes.add(pao);
        ingredientes.add(linguica);
        ingredientes.add(batataPalha);
        itens.add(item);

        promocao = new Promocao(nome, itens, tipoCalculo, baseCalculo);

        BigDecimal expected = new BigDecimal("15.80");
        BigDecimal actual = promocao.calculaPreco();

        assertEquals(expected, actual);
    }

}
