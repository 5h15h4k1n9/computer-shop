package ru.hh.spb.computershop.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Component
@EnableWebMvc
public class SwaggerConfiguration {

    @Value("${app.version}")
    private String appVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("ru.hh.spb.computershop.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfoMetaData())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfoMetaData() {
        return new ApiInfoBuilder()
                .title("Computer Shop")
                .description("API for Computer Shop")
                .contact(new Contact("Roman Shishkin", "https://github.com/5h15h4k1n9", "romashkin.2001@yandex.ru"))
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                .version(appVersion)
                .build();

    }
}