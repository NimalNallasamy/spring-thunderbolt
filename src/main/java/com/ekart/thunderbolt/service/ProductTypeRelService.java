package com.ekart.thunderbolt.service;

import com.ekart.thunderbolt.DO.ProductTypeRelDO;
import com.ekart.thunderbolt.DO.ProductTypeRelResponseDO;
import com.ekart.thunderbolt.entity.ProductTypeRel;
import com.ekart.thunderbolt.mapper.ProductsTypeRelMapper;
import com.ekart.thunderbolt.repository.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductTypeRelService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductTypeRelService.class);
    private final ProductTypeRelRepository productTypeRelRepository;
    private final ProductsRepository productsRepository;
    private final ProductBrandRepository productBrandRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductSubTypeRepository productSubTypeRepository;
    private final ProductsTypeRelMapper productsTypeRelMapper;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<ProductTypeRelResponseDO> getAllMappings(){
        return productTypeRelRepository.findAll()
                .stream()
                .map(productsTypeRelMapper ::mapDbToDo)
                .collect(Collectors.toList());
    }

    public void patchMappings(){

    }

    public void deleteMappings(@PathVariable Long id){

    }

    public ProductTypeRelResponseDO addNewMapping(ProductTypeRelDO productTypeRelDO){
        ProductTypeRel savedProductTypeRel = productTypeRelRepository.save(productsTypeRelMapper.mapDoToDb(productTypeRelDO));
        return productsTypeRelMapper.mapDbToDo(savedProductTypeRel);
    }

    public void getMappingByProductName(@PathVariable String productName){

    }

    public void getMappingByProductId(@PathVariable Long id){

    }

    public void getMappingByProductTypeName(@PathVariable String productTypeName){

    }

    public void getMappingByProductTypeId(@PathVariable Long id){

    }

    public void getMappingByProductBrandName(@PathVariable String productBrandName){

    }

    public void getMappingByProductBrandId(@PathVariable Long id){

    }

    public void getMappingByProductSubTypeName(@PathVariable String productSubTypeName){

    }

    public void getMappingByProductSubTypeId(@PathVariable Long id){

    }

}
