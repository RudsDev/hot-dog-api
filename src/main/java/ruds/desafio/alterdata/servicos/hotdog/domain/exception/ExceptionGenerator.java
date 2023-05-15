package ruds.desafio.alterdata.servicos.hotdog.domain.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionGenerator {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void generate(String body, HttpStatus status) {
        String message = "Houve um erro ao fazer a requisição";
        
        if(body != null) {
            JsonNode node = null;            
            try {
                ObjectMapper mapper = new ObjectMapper();
                node = mapper.readTree(body);                
            } catch(JsonProcessingException e) {
                LOGGER.error("erro ao processar o objeto");
            }
            
            if(node != null) {
                if(node.has("message")) {
                    message = node.get("message").asText();
                }           
            }
        } 
        
        throw new ServidorException(message, status);
    }
}
