package com.example.hw3.service;

import com.example.hw3.domain.Employee;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParsingService {
    /*
    Receive Json response and convert it into required model
     */
    String parse(String url);
    List<Employee> getCustomer(String url) throws JSONException;
    List<Employee> getCustomerByAge(String url,int age) throws JSONException;
//    Object parse(String url);
}
