package com.ekart.thunderbolt.repository;

import com.ekart.thunderbolt.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductId(Long productId);

    Optional<Product> findByProductName(String productName);

}
