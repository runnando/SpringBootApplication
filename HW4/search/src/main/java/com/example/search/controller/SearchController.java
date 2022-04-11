package com.example.search.controller;

import com.example.search.service.SearchWeatherService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class SearchController {

    private final SearchWeatherService weatherService;

    @Autowired
    public SearchController(SearchWeatherService weatherService) {
        this.weatherService = weatherService;
    }
    //2379574


    @GetMapping("/weather/search")
    public CompletableFuture<ResponseEntity<?>> getDetails(@RequestParam(value = "city") List<String> cityList) {
        //TODO
        return weatherService.findCitiesByName(cityList).thenApply(ResponseEntity::ok);


    }
    @GetMapping("/weather/search/{id}")
    public CompletableFuture<ResponseEntity<?>> getDetails(@PathVariable int id) {
        //TODO
        return weatherService.findCitiesById(id).thenApply(ResponseEntity::ok);


    }


}
