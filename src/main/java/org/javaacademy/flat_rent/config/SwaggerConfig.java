package org.javaacademy.flat_rent.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server flatRent = new Server();
        flatRent.setUrl("http://localhost:8080");
        flatRent.setDescription("Основной сервер!");

        Contact contact = new Contact();
        contact.setEmail("test@mail.ru");
        contact.setName("Астапов Алексей");

        Info info = new Info()
                .title("Сервис суточного бронирования квартир и комнат")
                .version("0.0.1")
                .contact(contact)
                .description("Апи сервиса по бронированию квартир и комнат");
        return new OpenAPI().info(info).servers(List.of(flatRent));
    }
}
