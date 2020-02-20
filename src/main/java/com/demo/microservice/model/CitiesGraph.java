package com.demo.microservice.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CitiesGraph {

    private Set<String> cities;
    private Map<String, Set<String>> adjCities;

    public CitiesGraph() {
        cities = new HashSet<>();
        adjCities = new HashMap<>();
    }

    public void addEdge(String v, String w) {
        cities.add(v);
        cities.add(w);

        adjCities.computeIfAbsent(v, k -> new HashSet<>()).add(w);
        adjCities.computeIfAbsent(w, k -> new HashSet<>()).add(v);
    }

    public Set<String> getCities() {
        return cities;
    }

    public Map<String, Set<String>> getAdjCities() {
        return adjCities;
    }

    @Override
    public String toString() {
        return "CitiesGraph{" +
                "cities=" + cities +
                ", adjCities=" + adjCities +
                '}';
    }
}
