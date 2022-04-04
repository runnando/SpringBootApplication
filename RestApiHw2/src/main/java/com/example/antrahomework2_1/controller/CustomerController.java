package com.example.antrahomework2_1.controller;

import com.example.antrahomework2_1.domain.Customer;
import com.example.antrahomework2_1.domain.CustomerDTO;
import com.example.antrahomework2_1.domain.Product;
import com.example.antrahomework2_1.domain.ProductDTO;
import com.example.antrahomework2_1.exception.ResourceNotFoundException;
import com.example.antrahomework2_1.repository.CustomerRepository;
import com.example.antrahomework2_1.repository.ProductRepository;
import com.example.antrahomework2_1.service.CustomerService;
import com.example.antrahomework2_1.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @Autowired
    public ProductService productService;

    @Autowired
    public CustomerRepository customerrepository;
    @Autowired
    public ProductRepository productrepository;

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customer){
        if(customer == null){
            throw new ResourceNotFoundException("There is not customer information insert");
        }
        return new ResponseEntity<>(customerService.saveCustomer(customer),HttpStatus.CREATED);
    }


    @PostMapping("/addCustomers")

    public ResponseEntity<List<CustomerDTO>> addCustomers(@RequestBody List<Customer> customers){
        if(customers == null){
            throw new ResourceNotFoundException("Input list is empty!");
        }
        return new ResponseEntity<List<CustomerDTO>>(customerService.saveCustomers(customers),HttpStatus.CREATED);
    }


    @GetMapping("/customers")
    public List<CustomerDTO> findAllCustomers(){
        List<CustomerDTO> listOfCto = customerService.getCustomers();

        return listOfCto;
    }

    @GetMapping("/customerById/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        CustomerDTO dto = customerService.getCustomerById(id);
        if(dto == null){
            throw new ResourceNotFoundException("(Custom Message)Can not find customer with id :" + id);
        }
        return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/customerByName/{name}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String name) {
        CustomerDTO dto = customerService.getCustomerByName(name);
        if(dto == null){
            throw new ResourceNotFoundException("(Custom Message)Can not find customer with name :" + name);
        }
        return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
    }

    @PutMapping("/updatecustomer")
    public CustomerDTO updateProduct(@RequestBody CustomerDTO cto){
        int theId = cto.getCustomerid();
        CustomerDTO tempCto = customerService.getCustomerById(theId);
        if(tempCto == null){
            throw new ResourceNotFoundException("(Custom Message)Can not update customer with id :" + theId);
        }
        return customerService.updateCustomer(cto);
    }


    @PutMapping("/{customerid}/products/{productid}")
    public ResponseEntity<CustomerDTO> putProductToCustomer(
            @PathVariable int customerid,
            @PathVariable int productid
    ){
        CustomerDTO cto = customerrepository.findById(customerid).get();
        ProductDTO pto = productrepository.findById(productid).get();
        if(cto == null){
            throw new ResourceNotFoundException("(Custom Message)customer id " + customerid + " does not exist.");
        }
        if(pto == null){
            throw new ResourceNotFoundException("(Custom Message)product id " + productid + " does not exist.");
        }
        cto.enrolledProduct(pto);
        return new ResponseEntity<>(customerService.saveCustomer(cto),HttpStatus.CREATED);
    }


    @DeleteMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable int id){
        CustomerDTO cto = customerService.getCustomerById(id);
        if(cto == null){
            throw new ResourceNotFoundException("(Custom Message)Can not delete customer with id :" + id);
        }
        return customerService.deleteCustomer(id);
    }
}
