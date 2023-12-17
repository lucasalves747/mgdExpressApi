package br.com.api.mgdexpress.MGD.EXPRESS.service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cors {



    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addServersItem(new Server().url("https://mgdexpressapi-production.up.railway.app/"))
                .addServersItem(new Server().url("http://localhost:8080"));
    }

}
