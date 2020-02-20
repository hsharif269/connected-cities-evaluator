package com.demo.microservice.common.utils;

public class TestUtils {

    public static String getApiUri(String origin, String destination) {
        return "/connected?origin=" + origin + "&destination=" + destination;
    }
}
