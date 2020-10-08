package com.ekart.thunderbolt.repository;

import com.ekart.thunderbolt.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    Optional<ProductType> findByTypeId(Long typeId);

    Optional<ProductType> findByTypeName(String typeName);

}
