package com.example.cfinal.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("API REST Full | Java | CoderHouse").version("1.0.1")
						.description("La API REST proporciona endpoints para administrar pedidos y"
								+ "clientes relacionados a los mismos")
						.contact(new Contact().name("Héctor Flores").email("ifr.hector@gmail.com")
								.url("https://nabit.mx"))
						.license(new License().name("Licencia")
								.url("https://dummy.com")))
				.servers(List.of(new Server().url("http://localhost:8080").description("Servidor Local")));
	}
}