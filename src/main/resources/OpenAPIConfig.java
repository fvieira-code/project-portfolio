package br.com.brasilprev.riscoposvendabbservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${risco-pos-venda-bb.openapi.dev-url}")
    private String devUrl;

    @Value("${risco-pos-venda-bb.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URLs em desenvolvimento");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("URLs em produção");

        Info info = new Info()
                .title("API Risco Pós Venda BB")
                .version("0.0.1")
                .description("Esta API exibe os endpoints do projeto Risco Pós Venda BB.");

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
