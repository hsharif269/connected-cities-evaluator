package com.demo.microservice.controller.integration;

import com.demo.microservice.app.ConnectedCitiesEvaluatorApplication;
import com.demo.microservice.common.utils.TestUtils;
import com.demo.microservice.service.ConnectedCitiesEvaluatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = ConnectedCitiesEvaluatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ConnectedCitiesEvaluatorControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ConnectedCitiesEvaluatorService connectedCitiesEvaluatorService;

    @Test
    public void isConnectedCitiesTest() {

        BDDMockito.given(connectedCitiesEvaluatorService.isConnectedCities(Mockito.anyString(), Mockito.anyString())).willReturn(Boolean.TRUE);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(TestUtils.getApiUri("philly", "boston"), String.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), "yes");

    }
}
