package com.example.hw3.controller;

import com.example.hw3.domain.Employee;
import com.example.hw3.service.ParsingService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    private static final String MAIN_PAGE = "main";
    private static final String JSON_EMPLOYEE_URL = "http://dummy.restapiexample.com/api/v1/employees";
    @Autowired
    private ParsingService parsingService;

    @GetMapping("/employee")
    public List<Employee> findAllEmployees() throws JSONException {
        List<Employee> employees = parsingService.getCustomer(JSON_EMPLOYEE_URL);
        return employees;
    }

    @GetMapping("/employee/{age}")
    public List<Employee> findAllEmployeesByAge(@PathVariable int age) throws JSONException {
        List<Employee> employees = parsingService.getCustomerByAge(JSON_EMPLOYEE_URL,age);
        return employees;
    }



}
