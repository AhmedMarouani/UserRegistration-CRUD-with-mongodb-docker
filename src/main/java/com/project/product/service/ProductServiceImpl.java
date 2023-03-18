package com.project.product.service;

import com.project.product.model.Product;
import com.project.product.repository.ProductRepository;
import com.project.product.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ResponseEntity<MessageResponse> editProduct(Integer id, Product product) {
        Optional<Product> editedProduct = productRepository.findById(id);
        if(editedProduct.isPresent()){
            Product existingProduct = editedProduct.orElseThrow(()->new RuntimeException("product not fount"));
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
            MessageResponse messageResponse = new MessageResponse("Product edited succesfully");
            return ResponseEntity.ok(messageResponse);
        }else {
            throw new ResolutionException("product not found");
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteProduct(Integer id) {
        productRepository.deleteById(id);
        MessageResponse messageResponse = new MessageResponse("Product deleted succesfully");
        return ResponseEntity.ok(messageResponse);
    }
}
