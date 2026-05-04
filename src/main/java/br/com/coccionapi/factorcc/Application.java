package br.com.coccionapi.factorcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = "Calculo do fator de correção e cocção", version = "1.2.15", description = "Esta API é responsavel em calcular o fator de cocção e correção dos ingredientes.", license = @License(name = "Alexan License", url = "https://www.linkedin.com/in/alexan-limaa/")), security = {
		//@SecurityRequirement(name = "bearerAuth")
})
// @SecuritySchemes(value = {
// 		@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT") })
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
