package com.order.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "ORDER-SERVICE"),
        servers = @Server(
                description = "Order Service Swagger url",
                url = "http://localhost:9002/orders/"
        )
)
@Configuration
public class SwaggerConfig {
}
