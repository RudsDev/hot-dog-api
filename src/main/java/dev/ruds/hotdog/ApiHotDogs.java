package dev.ruds.hotdog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan("dev.ruds.hotdog.domain.models")
public class ApiHotDogs extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ApiHotDogs.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiHotDogs.class);
	}

}
