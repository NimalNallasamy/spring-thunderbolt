package com.ekart.thunderbolt.repository;

import com.ekart.thunderbolt.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    Optional<Products> findByProductId(Long productId);

    Optional<Products> findByProductName(String productName);

}
