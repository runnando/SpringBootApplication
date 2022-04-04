package com.example.antrahomework2_1.controller;

import com.example.antrahomework2_1.domain.Product;
import com.example.antrahomework2_1.domain.ProductDTO;
import com.example.antrahomework2_1.exception.ResourceNotFoundException;
import com.example.antrahomework2_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PostMapping("/addProduct")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product){
        if(product == null){
            throw new ResourceNotFoundException("There is no product information insert!");
        }
        return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.CREATED);
    }


    @PostMapping("/addProducts")

    public ResponseEntity<List<ProductDTO>> addProducts(@RequestBody List<Product> products){
        if(products == null){
            throw new ResourceNotFoundException("Input list is empty!");
        }
        return new ResponseEntity<List<ProductDTO>>(productService.saveProducts(products),HttpStatus.CREATED);
    }


    @GetMapping("/products")
    public List<ProductDTO> findAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        ProductDTO pto = productService.getProductById(id);
        if(pto == null){
            throw new ResourceNotFoundException("(Custom Message)Can not find product with id :" + id);
        }
        return new ResponseEntity<ProductDTO>(pto, HttpStatus.OK);
    }

    @GetMapping("/productByName/{name}")
    public ResponseEntity<ProductDTO> getProductByName(@PathVariable String name){
        ProductDTO pto = productService.getProductByName(name);
        if(pto == null){
            throw new ResourceNotFoundException("(Custom Message)Can not find product with name :" + name);
        }
        return new ResponseEntity<ProductDTO>(pto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO pto){

        int theId = pto.getId();
        ProductDTO tempPto = productService.getProductById(theId);
        if(tempPto == null){
            throw new ResourceNotFoundException("(Custom Message)Can not update product with id :" + theId);
        }
        return productService.updateProduct(pto);
    }




    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        ProductDTO pto = productService.getProductById(id);
        if(pto == null){
            throw new ResourceNotFoundException("(Custom Message)Can not delete product with id :" + id);
        }
        return productService.deleteProduct(id);
    }



}
