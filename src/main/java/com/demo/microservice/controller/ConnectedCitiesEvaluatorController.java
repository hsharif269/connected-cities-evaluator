package com.demo.microservice.controller;

import com.demo.microservice.service.ConnectedCitiesEvaluatorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectedCitiesEvaluatorController {

    private static final String NO = "no";
    private static final String YES = "yes";

    @Autowired
    private ConnectedCitiesEvaluatorService connectedCitiesEvaluatorService;

    @ApiOperation(value = "Checks if two cities are connected or not", response = String.class)
    @GetMapping(value = "/connected", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> isConnectedCities(@RequestParam(value = "origin", required = true) String origin,
                                                    @RequestParam(value = "destination", required = true) String destination) {
        return new ResponseEntity<>(connectedCitiesEvaluatorService.isConnectedCities(origin, destination) ? YES : NO, HttpStatus.OK);
    }
}
