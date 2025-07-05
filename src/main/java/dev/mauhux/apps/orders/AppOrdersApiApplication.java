package dev.mauhux.apps.orders;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppOrdersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppOrdersApiApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Orders API")
                        .version("1.0.0")
                        .description("API Orders de gestion")
                        .termsOfService("http://www.orderslab.io/terms"));
    }
}
