package com.sukhjit.assignment.controller;

import com.sukhjit.assignment.model.Product;
import com.sukhjit.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/v1/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/v1/products")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

}
