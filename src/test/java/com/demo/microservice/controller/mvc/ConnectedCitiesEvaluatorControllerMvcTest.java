package com.demo.microservice.controller.mvc;

import com.demo.microservice.common.utils.TestUtils;
import com.demo.microservice.controller.ConnectedCitiesEvaluatorController;
import com.demo.microservice.service.ConnectedCitiesEvaluatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ConnectedCitiesEvaluatorControllerMvcTest {

    @InjectMocks
    private ConnectedCitiesEvaluatorController connectedCitiesEvaluatorController;

    @Mock
    private ConnectedCitiesEvaluatorService connectedCitiesEvaluatorService;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(connectedCitiesEvaluatorController).build();
    }

    @Test
    public void isConnectedCitiesTest() throws Exception {
        Mockito.when(connectedCitiesEvaluatorService.isConnectedCities(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.TRUE);
        MockHttpServletResponse response = mvc.perform(get(TestUtils.getApiUri("boston", "Philly"))).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "yes");
    }

    @Test
    public void isConnectedCitiesTest2() throws Exception {
        Mockito.when(connectedCitiesEvaluatorService.isConnectedCities(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.FALSE);
        MockHttpServletResponse response = mvc.perform(get(TestUtils.getApiUri("boston", "Philly"))).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "no");
    }

}
