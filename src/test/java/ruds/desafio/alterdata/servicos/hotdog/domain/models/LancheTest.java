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


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
    Lanche.class
})
public class LancheTest {

    Lanche lanche;
    
    Ingrediente pao = new Ingrediente("Pão", new BigDecimal("2.00"));
    Ingrediente batataPalha = new Ingrediente("Batata palha", new BigDecimal("0.40"));
    Ingrediente linguica = new Ingrediente("Linguiça", new BigDecimal("3.00"));

    Ingrediente ovoCodornaNegativo = new Ingrediente("Ovo Codorna", new BigDecimal("-0.30"));
    Ingrediente pureBatataNegativo = new Ingrediente("Purê de Batata", new BigDecimal("-1.00"));

    @BeforeEach
    void setup() {
        lanche = new Lanche();
    }

    @Test
    void calculaPrecoComNenhumIngrediente() {

        List<Ingrediente> ingredientes = new ArrayList<>();

        lanche.addIngredientes(ingredientes);

        BigDecimal resultado = lanche.calculaPreco();
        BigDecimal valorEsperado = BigDecimal.valueOf(0);

        assertEquals(valorEsperado, resultado);
    }

    @Test
    void calculaPrecoComUmIngrediente() {

        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(pao);

        lanche.addIngredientes(ingredientes);

        BigDecimal resultado = lanche.calculaPreco();
        BigDecimal valorEsperado = new BigDecimal("2.00");

        assertEquals(valorEsperado, resultado);
    }

    @Test
    void calculaPrecoComVariosIngredientes() {

        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(pao);
        ingredientes.add(batataPalha);
        ingredientes.add(linguica);

        lanche.addIngredientes(ingredientes);

        BigDecimal resultado = lanche.calculaPreco();
        BigDecimal valorEsperado = new BigDecimal("5.40");
        
        assertEquals(valorEsperado, resultado);
    }

    @Test
    void calculaPrecoComIngredientesPrecosNulos() {

        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(ovoCodornaNegativo);
        ingredientes.add(pureBatataNegativo);

        lanche.addIngredientes(ingredientes);

        BigDecimal resultado = lanche.calculaPreco();
        BigDecimal valorEsperado = new BigDecimal("-1.30");
        
        assertEquals(valorEsperado, resultado);
    }
    
}
