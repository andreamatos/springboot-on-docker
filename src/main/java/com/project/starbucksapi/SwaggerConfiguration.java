package com.project.starbucksapi;

import java.io.FileReader;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SwaggerConfiguration.class);
	
	@Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }
    private ApiInfo apiInfo() {
        StringBuilder titulo = new StringBuilder();
        titulo.append("PROJETO CORPORATIVO");
        StringBuilder version = new StringBuilder();
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            titulo.append("(");
            titulo.append(StringUtils.upperCase(model.getArtifactId()));
            titulo.append(")");
            version.append("V-").append(model.getVersion());
        } catch (Exception e) {
            LOGGER.error("ERRO AO BUSCAR INFO DO POM ");
        }
        return new ApiInfoBuilder().title(titulo.toString()).description("Mapeamento dos Endpoints do projeto")
                .version(version.toString()).termsOfServiceUrl("http://terms-of-services.url").license("LICENSE")
                .licenseUrl("http://url-to-license.com").build();
    }

}