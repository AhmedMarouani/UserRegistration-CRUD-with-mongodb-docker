package com.project.product.controller;

import com.project.product.model.Product;
import com.project.product.response.MessageResponse;
import com.project.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Products")
@RestController
@AllArgsConstructor

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts(){
        List<Product> products =  productService.getProducts();
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Integer id){
        Product products =  productService.getProduct(id);
        return products;
    }

    @GetMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        Product products =  productService.saveProduct(product);
        return products;
    }

    @PutMapping("/editProduct/{id}")
    public ResponseEntity<MessageResponse> editProduct(@PathVariable("id") Integer id, @RequestBody Product product ){
        ResponseEntity<MessageResponse> response = productService.editProduct(id, product);

        return response;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("id") Integer id){
        ResponseEntity<MessageResponse> response = productService.deleteProduct(id);

        return response;
    }
}
