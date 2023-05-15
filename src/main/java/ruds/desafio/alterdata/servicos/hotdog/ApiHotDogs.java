package ruds.desafio.alterdata.servicos.hotdog;

import java.time.Instant;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ruds.desafio.alterdata.servicos.hotdog.domain.models")
public class ApiHotDogs {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public final static Instant START_TIME = Instant.now();

    public static void main(String[] args) {
    	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    	
        SpringApplication.run(ApiHotDogs.class, args);
        ApiHotDogs main = new ApiHotDogs();
        main.iniciar();
    }

    public void iniciar() {
        LOGGER.info("API DE HOT-DOGS INICIADA");
    }

}
