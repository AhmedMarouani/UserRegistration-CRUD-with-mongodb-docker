package com.project.product.service;

import com.project.product.model.Product;
import com.project.product.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(Integer id);

    Product saveProduct(Product product);

    ResponseEntity<MessageResponse> editProduct(Integer id, Product product);

    ResponseEntity<MessageResponse> deleteProduct(Integer id);


}
