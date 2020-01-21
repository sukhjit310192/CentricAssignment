package com.sukhjit.assignment.repository;

import com.sukhjit.assignment.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByCategoryOrderByCreatedAtDesc(Pageable pageable, String category);
}
