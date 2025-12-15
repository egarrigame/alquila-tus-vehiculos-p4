package com.alquilaTusVehiculos.alquila_tus_vehiculos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Alquiler de vehiculos")
                        .version("1.0")
                        .description("Servicio API REST para la app de alquiler de vehiculos")
                        .contact(new Contact()
                                .name("Eduard & Ignacio")
                                .email("eduigna@mail.com")));
    }
}
