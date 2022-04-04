package com.example.antrahomework2_1.repository;

import com.example.antrahomework2_1.domain.Customer;
import com.example.antrahomework2_1.domain.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<CustomerDTO,Integer> {

    // Be careful of name style!!!
    CustomerDTO findByCustomername(String name);
}
