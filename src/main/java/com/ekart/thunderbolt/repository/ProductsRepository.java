package com.ekart.thunderbolt.repository;

import com.ekart.thunderbolt.entity.Products;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    Optional<Products> findByProductId(Long productId);

    Optional<Products> findByProductName(String productName);

}
