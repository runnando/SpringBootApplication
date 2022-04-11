package com.example.search.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface SearchWeatherService {
//    List<List<Integer>> findCityIdByName(List<String> city);
//    Map<String, Map> findCityNameById(int id);
    CompletableFuture<Map<String,Map>> findCitiesById(int id);
    CompletableFuture<Map<Integer, Map<String, Map>>> findCitiesByName(List<String> cities);

}
