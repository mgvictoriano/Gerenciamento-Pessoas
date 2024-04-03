package tech.mGaia.GerenciamentoPessoas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe de configuração para o Swagger.
 * Habilita o Swagger na aplicação e configura a geração da documentação para as APIs REST.
 * @Author @mgvictoriano
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configuração do Swagger para a geração da documentação das APIs REST.
     * @return Um objeto Docket configurado com as informações necessárias para a geração da documentação.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("tech.mGaia.GerenciamentoPessoas.controller")) // Define o pacote base onde estão localizados os controladores
                .paths(PathSelectors.any()) // Define que todas as URLs serão documentadas
                .build();
    }
}