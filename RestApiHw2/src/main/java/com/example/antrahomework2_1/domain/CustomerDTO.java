package com.example.antrahomework2_1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "antra_customer")
public class CustomerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerid;
    private String customername;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "interesting_product",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )


    private Set<ProductDTO> theProduct = new HashSet<>();
    public CustomerDTO(){
    }
    public CustomerDTO(Customer c){
        this.customerid = c.getCustomerid();
        this.customername = c.getCustomername();
    }

    public void enrolledProduct(ProductDTO pto){
        theProduct.add(pto);
    }
}
