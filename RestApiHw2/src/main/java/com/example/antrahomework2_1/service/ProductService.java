package com.example.antrahomework2_1.service;

import com.example.antrahomework2_1.domain.Product;
import com.example.antrahomework2_1.domain.ProductDTO;
import com.example.antrahomework2_1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;


    public ProductDTO saveProduct(ProductDTO product){
        return repository.save(product);
    }

    public List<ProductDTO> saveProducts(List<Product> products){
        List<ProductDTO> ps = products.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());

        return repository.saveAll(ps);
    }

    public List<ProductDTO> getProducts(){
        return repository.findAll();
    }

    public ProductDTO getProductById(int id){

        return repository.findById(id).orElse(null);

    }

    public ProductDTO getProductByName(String name){

        return repository.findByName(name);

    }

    public String deleteProduct(int id){
        repository.deleteById(id);
        return "Product: " + id +  "Removed";
    }

    public ProductDTO updateProduct(ProductDTO pto){
        ProductDTO newPto = repository.findById(pto.getId()).orElse(null);
        newPto.setName(pto.getName());
        newPto.setQuantity(pto.getQuantity());
        newPto.setPrice(pto.getPrice());
        return repository.save(newPto);
    }
}
