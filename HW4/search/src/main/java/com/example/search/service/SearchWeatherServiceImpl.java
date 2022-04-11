package com.example.search.service;

import com.example.search.config.SearchEndPointConfig;
import com.example.search.pojo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.EndpointConfig;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchWeatherServiceImpl implements SearchWeatherService{

    private final RestTemplate restTemplate;

    public SearchWeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Object target;
    Logger logger = LoggerFactory.getLogger(SearchWeatherServiceImpl.class);

    @Override
    @Async
    public CompletableFuture<Map<String,Map>> findCitiesById(int id){
        logger.info("Get city by id: " + Thread.currentThread().getName());
        Map<String,Integer> param = new HashMap<>();
        param.put("id",id);
        return CompletableFuture.completedFuture(restTemplate.getForObject(SearchEndPointConfig.GET_CITIES_BY_ID_URL,Map.class,param));

    }

    @Override
    public CompletableFuture<Map<Integer, Map<String, Map>>> findCitiesByName(List<String> cities){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SearchEndPointConfig.GET_CITIES_BY_NAME_URL);
        builder.queryParam("city",String.join(",",cities));
        String parsedURI = builder.build().encode().toUriString();
        Map<Integer, Map<String, Map>> ans = restTemplate.getForObject(parsedURI, HashMap.class);
        return CompletableFuture.completedFuture(ans);


    }


}
