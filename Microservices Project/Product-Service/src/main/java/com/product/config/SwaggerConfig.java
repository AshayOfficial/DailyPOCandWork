package com.product.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "PRODUCT-SERVICE"),
        servers = {
                @Server(
                        description = "Swagger url for local system",
                        url = "http://localhost:9001/products/"
                )
        }
)
@Configuration
public class SwaggerConfig {

}
