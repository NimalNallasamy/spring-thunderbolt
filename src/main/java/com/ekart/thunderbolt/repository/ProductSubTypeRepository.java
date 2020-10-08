package com.ekart.thunderbolt.repository;

import com.ekart.thunderbolt.entity.ProductSubType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSubTypeRepository extends JpaRepository<ProductSubType, Long> {

    Optional<ProductSubType> findBySubTypeId(Long subTypeId);

    Optional<ProductSubType> findBySubTypeName(String subTypeName);

}
