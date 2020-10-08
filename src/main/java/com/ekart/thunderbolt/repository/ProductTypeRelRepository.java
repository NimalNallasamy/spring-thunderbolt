package com.ekart.thunderbolt.repository;

import com.ekart.thunderbolt.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRelRepository extends JpaRepository<ProductTypeRel, Long> {

    ProductTypeRel findByTypeRelId(Long typeRelId);

    List<ProductTypeRel> findByProducts(Products products);

    List<ProductTypeRel> findByProductType(ProductType productType);

    List<ProductTypeRel> findByProductSubType(ProductSubType productSubType);

    List<ProductTypeRel> findByProductBrand(ProductBrand productBrand);

}
