package com.sukhjit.assignment.repository;

import com.sukhjit.assignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {


}
