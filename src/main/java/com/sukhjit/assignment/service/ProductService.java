package com.sukhjit.assignment.service;

import com.sukhjit.assignment.model.Product;
import com.sukhjit.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products :: add);
        return products;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> searchProductByCategory(String category, Integer pageNo, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Product> listResult = productRepository.findByCategoryOrderByCreatedAtDesc(pageable, category);

        return listResult;
    }

}
