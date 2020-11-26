package com.ekart.thunderbolt.repository;

import com.ekart.thunderbolt.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTypeRelRepository extends JpaRepository<ProductTypeRel, Long> {

    Optional<ProductTypeRel> findByTypeRelId(Long typeRelId);

    Optional<List<ProductTypeRel>> findByProducts(Products products);

    Optional<List<ProductTypeRel>> findByProductType(ProductType productType);

    Optional<List<ProductTypeRel>> findByProductSubType(ProductSubType productSubType);

    Optional<List<ProductTypeRel>> findByProductBrand(ProductBrand productBrand);

}
