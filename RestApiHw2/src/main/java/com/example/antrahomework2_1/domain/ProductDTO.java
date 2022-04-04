package com.example.antrahomework2_1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;


@Data
@AllArgsConstructor
@Entity
@Table(name = "antra_product")
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int quantity;
    private double price;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "theProduct")
    private Set<CustomerDTO> theCustomer = new HashSet<>();
    public ProductDTO(){
    }
    public ProductDTO(Product p){

        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.quantity = p.getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductDTO pto = (ProductDTO) o;

        return Objects.equals(id, pto.id) && Objects.equals(name, pto.name) && Objects.equals(price, pto.price) && Objects.equals(quantity, pto.quantity);
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(),id,name,price,quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
