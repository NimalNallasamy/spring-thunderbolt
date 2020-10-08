package com.ekart.thunderbolt.mapper;

import com.ekart.thunderbolt.DO.ProductTypeRelDO;
import com.ekart.thunderbolt.DO.ProductTypeRelResponseDO;
import com.ekart.thunderbolt.entity.*;
import com.ekart.thunderbolt.repository.ProductBrandRepository;
import com.ekart.thunderbolt.repository.ProductSubTypeRepository;
import com.ekart.thunderbolt.repository.ProductTypeRepository;
import com.ekart.thunderbolt.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring")
//@AllArgsConstructor
public abstract class ProductsTypeRelMapper {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductSubTypeRepository productSubTypeRepository;
    @Autowired
    private ProductBrandRepository productBrandRepository;

    @Mapping(target = "products", expression = "java(getProducts(productTypeRelDO))")
    @Mapping(target = "productType", expression = "java(getProductType(productTypeRelDO))")
    @Mapping(target = "productSubType", expression = "java(getProductSubType(productTypeRelDO))")
    @Mapping(target = "productBrand", expression = "java(getProductBrand(productTypeRelDO))")
    public abstract ProductTypeRel mapDoToDb(ProductTypeRelDO productTypeRelDO);

    public Products getProducts(ProductTypeRelDO productTypeRelDO){
        if(productTypeRelDO.getProductId() != null){
            return productsRepository.findByProductId(productTypeRelDO.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        else if(productTypeRelDO.getProductName() != null){
            return productsRepository.findByProductName(productTypeRelDO.getProductName())
                    .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to fetch products related data since invalid input");
        }
    }

    public ProductType getProductType(ProductTypeRelDO productTypeRelDO){
        if(productTypeRelDO.getProductId() != null){
            return productTypeRepository.findByTypeId(productTypeRelDO.getProductTypeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        else if(productTypeRelDO.getProductName() != null){
            return productTypeRepository.findByTypeName(productTypeRelDO.getProductTypeName())
                    .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to fetch Product Type related data since invalid input");
        }
    }

    public ProductSubType getProductSubType(ProductTypeRelDO productTypeRelDO){
        if(productTypeRelDO.getProductId() != null){
            return productSubTypeRepository.findBySubTypeId(productTypeRelDO.getProductTypeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        else if(productTypeRelDO.getProductName() != null){
            return productSubTypeRepository.findBySubTypeName(productTypeRelDO.getProductTypeName())
                    .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to fetch Product Type related data since invalid input");
        }
    }

    public ProductBrand getProductBrand(ProductTypeRelDO productTypeRelDO){
        if(productTypeRelDO.getProductId() != null){
            return productBrandRepository.findByBrandId(productTypeRelDO.getProductTypeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        else if(productTypeRelDO.getProductName() != null){
            return productBrandRepository.findByBrandName(productTypeRelDO.getProductTypeName())
                    .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to fetch Product Type related data since invalid input");
        }
    }

    // Conversion of Product Type Rel DB to Product Type Rel Response DO

    @Mapping(target = "typeRelId", source = "productTypeRel.typeRelId")
    @Mapping(target = "products", source = "productTypeRel.products")
    @Mapping(target = "productType", source = "productTypeRel.productType")
    @Mapping(target = "productBrand", source = "productTypeRel.productBrand")
    @Mapping(target = "productSubType", source = "productTypeRel.productSubType")
    public abstract ProductTypeRelResponseDO mapDbToDo(ProductTypeRel productTypeRel);

}