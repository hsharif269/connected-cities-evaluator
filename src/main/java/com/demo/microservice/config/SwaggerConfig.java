package com.demo.microservice.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Configuration for Swagger2.0
 *
 *
 */
@Configuration
public class SwaggerConfig {

    public static final String TITLE = "Connected Cities Evaluator Api";
    public static final String DESCRIPTION = "Api to evaluate if the two cities are connected";
    public static final String VERSION = "1.0";
    public static final String CONNECTED = "/connected";
    public static final String GROUP_NAME = "connectedCities";

    public Docket connectedCitiesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME)
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .build();
    }

    private Predicate<String> paths() {
        return regex(CONNECTED);
    }
}
