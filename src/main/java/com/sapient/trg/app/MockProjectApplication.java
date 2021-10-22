package com.sapient.trg.app;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@SpringBootApplication(scanBasePackages = "com.sapient")
@EntityScan(basePackages = "com.sapient.trg.entity")
@EnableJpaRepositories(basePackages = "com.sapient.trg.dao")
public class MockProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockProjectApplication.class, args);
		
		
	}
	@Bean
	public Docket openApiPetStore() {
		return new Docket(DocumentationType.OAS_30)
				.groupName("open-api-user-management")
				.select()
				.paths(rolePaths())
				.build();
	}

	private Predicate<String> rolePaths() {
		return regex(".*/usm/.*");
	}
}
