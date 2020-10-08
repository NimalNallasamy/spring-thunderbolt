package com.ekart.thunderbolt.repository;

import com.ekart.thunderbolt.entity.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {

    Optional<ProductBrand> findByBrandId(Long brandId);

    Optional<ProductBrand> findByBrandName(String brandName);

}
