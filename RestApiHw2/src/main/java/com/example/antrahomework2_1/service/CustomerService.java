package com.example.antrahomework2_1.service;

import com.example.antrahomework2_1.domain.Customer;
import com.example.antrahomework2_1.domain.CustomerDTO;

import com.example.antrahomework2_1.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository crepository;


    public CustomerDTO saveCustomer(CustomerDTO customer){return crepository.save(customer);}

    public List<CustomerDTO> saveCustomers(List<Customer> customers){
        List<CustomerDTO> cs = customers.stream().map(x -> new CustomerDTO(x)).collect(Collectors.toList());

        return crepository.saveAll(cs);
    }

    public List<CustomerDTO> getCustomers(){
        return crepository.findAll();
    }

    public CustomerDTO getCustomerById(int id){

        return crepository.findById(id).orElse(null);

    }

    public CustomerDTO getCustomerByName(String name){

        return crepository.findByCustomername(name);

    }

    public String deleteCustomer(int id){
        crepository.deleteById(id);
        return "Customer: " + id +  "Removed";
    }

    public CustomerDTO updateCustomer(CustomerDTO cto){
        CustomerDTO newCto = crepository.findById(cto.getCustomerid()).orElse(null);
        newCto.setCustomername(cto.getCustomername());

        return crepository.save(newCto);
    }


}
