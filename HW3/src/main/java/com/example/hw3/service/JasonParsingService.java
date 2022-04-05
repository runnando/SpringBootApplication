package com.example.hw3.service;

import com.example.hw3.domain.Employee;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JasonParsingService implements ParsingService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String parse(String url){
        return restTemplate.getForObject(url,String.class);
    }
    @Override
    public List<Employee> getCustomer(String url) throws JSONException {
        List<Employee> result = new ArrayList<>();
        String jsonString = parse(url);
        JSONObject obj = new JSONObject(jsonString);
        JSONArray employeeData = obj.getJSONArray("data");
        int size = employeeData.length();
        for(int i =0; i<size;i++){
            JSONObject tempObj = employeeData.getJSONObject(i);
            int tempId = Integer.valueOf(tempObj.getString("id"));
            String tempName = tempObj.getString("employee_name");
            int tempSalary = Integer.valueOf(tempObj.getString("employee_salary"));
            int tempAge = Integer.valueOf(tempObj.getString("employee_age"));
            String tempImage = tempObj.getString("profile_image");
            result.add(new Employee(tempId,tempName,tempSalary,tempAge,tempImage));
        }

        return result.stream().sorted((a,b)-> a.getEmployee_age() - b.getEmployee_age()).collect(Collectors.toList());
    }
    @Override
    public List<Employee> getCustomerByAge(String url, int age) throws JSONException{
        List<Employee> result = new ArrayList<>();
        String jsonString = parse(url);
        JSONObject obj = new JSONObject(jsonString);
        JSONArray employeeData = obj.getJSONArray("data");
        int size = employeeData.length();
        for(int i =0; i<size;i++){
            JSONObject tempObj = employeeData.getJSONObject(i);
            int tempId = Integer.valueOf(tempObj.getString("id"));
            String tempName = tempObj.getString("employee_name");
            int tempSalary = Integer.valueOf(tempObj.getString("employee_salary"));
            int tempAge = Integer.valueOf(tempObj.getString("employee_age"));
            String tempImage = tempObj.getString("profile_image");
            result.add(new Employee(tempId,tempName,tempSalary,tempAge,tempImage));
        }
        return result.stream().filter(x -> x.getEmployee_age() == age).collect(Collectors.toList());

    }
}
