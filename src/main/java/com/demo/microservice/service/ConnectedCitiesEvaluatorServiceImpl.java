package com.demo.microservice.service;

import com.demo.microservice.model.CitiesGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConnectedCitiesEvaluatorServiceImpl implements ConnectedCitiesEvaluatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedCitiesEvaluatorServiceImpl.class);
    public static final String DELIMITER = ",";

    @Value("classpath:static/city.txt")
    private Resource connectedCitiesFile;

    private CitiesGraph citiesGraph;

    @PostConstruct
    private void init() {
        try {
            LOGGER.info("Loading connected cities graph ...");
            citiesGraph = loadCitiesGraph(readFile());
            LOGGER.info("Done Loading Graph :: {}", citiesGraph.toString());
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to load connected cities graph: %s", e.getMessage()));
        }
    }

    @Override
    public Boolean isConnectedCities(String origin, String destination) {
        origin = origin.toLowerCase();
        destination = destination.toLowerCase();

        LOGGER.info("Evaluating if connection exists between {{}} and {{}} ", origin, destination);


        if (origin.equals(destination)) {
            LOGGER.info("Identical city {{}} passed for origin and destination", origin);
            return Boolean.TRUE;
        }

        if (!citiesGraph.getCities().contains(origin)) {
            LOGGER.info("Unrecognized origin:: {{}}", origin);
            return Boolean.FALSE;
        }

        if (!citiesGraph.getCities().contains(destination)) {
            LOGGER.info("Unrecognized destination:: {{}}", destination);
            return Boolean.FALSE;
        }

        return checkIndirectConnections(origin, destination);
    }

    /**
     * Uses BFS traversal to find the connection between the nodes
     *
     * @param origin
     * @param destination
     * @return
     */
    private Boolean checkIndirectConnections(String origin, String destination) {
        Set<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();

        visited.add(origin);
        queue.add(origin);

        while (!queue.isEmpty()) {
            //dequeue a vertix from queue
            String city = queue.poll();

            //check all the adjacent cities of dequeued vertix if they match the destination
            for (String adjCity : citiesGraph.getAdjCities().get(city)) {
                if (adjCity.equals(destination)) {
                    LOGGER.info("Given cities are connected!");
                    return Boolean.TRUE;
                }

                if (!visited.contains(adjCity)) {
                    visited.add(adjCity);
                    queue.add(adjCity);
                }
            }
        }
        return Boolean.FALSE;
    }

    private CitiesGraph loadCitiesGraph(List<String> connectedCitiesList) {
        CitiesGraph citiesGraph = new CitiesGraph();
        connectedCitiesList.stream().forEach(connectedCities -> {
            String[] cities = connectedCities.split(DELIMITER);
            citiesGraph.addEdge(cities[0].trim().toLowerCase(), cities[1].trim().toLowerCase());
        });
        return citiesGraph;
    }

    private List<String> readFile() throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(connectedCitiesFile.getInputStream()))) {
            return buffer.lines().filter(line -> line.split(DELIMITER).length == 2).collect(Collectors.toList());
        }
    }
}
