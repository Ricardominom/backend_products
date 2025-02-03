package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/**") // Ruta de tu API
						.allowedOrigins("https://frontend-products-xi.vercel.app") // Dominio del frontend
						.allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
						.allowedHeaders("*") // Encabezados permitidos
						.allowCredentials(true); // Permitir credenciales (si es necesario)
			}
		};
	}
}

