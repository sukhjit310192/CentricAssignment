package com.sukhjit.assignment.controller;

import com.sukhjit.assignment.model.Product;
import com.sukhjit.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/v1/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/v1/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping("/v1/products/{category}")
    public ResponseEntity<List<Product>> searchProductByCategory(@PathVariable String category){
         productService.searchProductByCategory(category);
        return new ResponseEntity<>(productService.getAllProducts()
                .stream().filter(x -> x.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparing(Product::getCreated_at).reversed())
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

}
