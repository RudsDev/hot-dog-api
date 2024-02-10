package dev.ruds.hotdog.api.v1;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ruds.hotdog.api.v1.ingredientes.IngredientesController;
import dev.ruds.hotdog.domain.records.IngredienteRecord;
import dev.ruds.hotdog.utils.mappers.IngredienteMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    IngredientesController.class,
    IngredienteMapper.class,
    ObjectMapper.class
})
public class IngredientesControllerTest {

    final private String URL = "/v1/ingredientes";

    private MockMvc mvc;

    private IngredienteRecord ingreditente;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    IngredientesController controller;

    @BeforeEach
    public void setup() {
        ingreditente = new IngredienteRecord("Orégano", "0.15"); 
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void deveRetornarStatus201ComObjetoMesmosValores_QuandoCriarTipoCorretamente() throws Exception {
        String json = mapper.writeValueAsString(ingreditente);
        
        var nome = ingreditente.nome();
        var preco = new BigDecimal(ingreditente.preco()).doubleValue();

        mvc.perform(
            post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8")
        )
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nome", is(nome))) 
        .andExpect(jsonPath("$.preco", is(preco)));
    }
}
