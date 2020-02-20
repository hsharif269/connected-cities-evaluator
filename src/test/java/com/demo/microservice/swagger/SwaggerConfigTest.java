package com.demo.microservice.swagger;

import com.demo.microservice.config.SwaggerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SwaggerConfigTest {

    @InjectMocks
    private SwaggerConfig swaggerConfig;

    @Test
    public void connectedCitiesApiTest() {
        Docket docket = swaggerConfig.connectedCitiesApi();
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
        assertEquals(SwaggerConfig.GROUP_NAME, docket.getGroupName());
    }

    @Test
    public void apiInfoTest() {
        ApiInfo apiInfo = swaggerConfig.apiInfo();
        assertEquals(SwaggerConfig.TITLE, apiInfo.getTitle());
        assertEquals(SwaggerConfig.DESCRIPTION, apiInfo.getDescription());
        assertEquals(SwaggerConfig.VERSION, apiInfo.getVersion());

    }

}
