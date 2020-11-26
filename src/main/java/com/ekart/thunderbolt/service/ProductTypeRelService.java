package com.ekart.thunderbolt.service;

import com.ekart.thunderbolt.DO.ProductTypeRelDO;
import com.ekart.thunderbolt.DO.ProductTypeRelResponseDO;
import com.ekart.thunderbolt.entity.*;
import com.ekart.thunderbolt.mapper.ProductsTypeRelMapper;
import com.ekart.thunderbolt.repository.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductTypeRelService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductTypeRelService.class);
    private final ProductTypeRelRepository productTypeRelRepository;
    private final ProductsRepository productsRepository;
    private final ProductBrandRepository productBrandRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductSubTypeRepository productSubTypeRepository;
    private final ProductsTypeRelMapper productsTypeRelMapper;


    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getAllMappings(){
        return productTypeRelRepository.findAll()
                .stream()
                .map(productsTypeRelMapper ::mapDbToDo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductTypeRelResponseDO getMappingByRelationId(Long relationId){
        return productsTypeRelMapper.mapDbToDo(productTypeRelRepository.findById(relationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT)));
    }

    @Transactional
    public ProductTypeRel patchMappings(Long relationId, ProductTypeRelDO productTypeRelDO){

        ProductTypeRel savedProductTypeRel = productTypeRelRepository.findByTypeRelId(relationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return productTypeRelRepository.save(productsTypeRelMapper.mapDoToDb(productTypeRelDO));
    }

    @Transactional
    public void deleteMappings(Long productTypeRelId){
        ProductTypeRel savedProductTypeRel = productTypeRelRepository.findByTypeRelId(productTypeRelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        productTypeRelRepository.delete(savedProductTypeRel);
    }

    @Transactional
    public ProductTypeRelResponseDO addNewMapping(ProductTypeRelDO productTypeRelDO){
        ProductTypeRel savedProductTypeRel = productTypeRelRepository.save(productsTypeRelMapper.mapDoToDb(productTypeRelDO));
        return productsTypeRelMapper.mapDbToDo(savedProductTypeRel);
    }

    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getMappingByProductName(@PathVariable String productName){
        Products savedProduct = productsRepository.findByProductName(productName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Product found for the given product Name ::: "+productName));

        return productTypeRelRepository.findByProducts(savedProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No Such Mapping for the product found"))
                .stream()
                .map(productsTypeRelMapper :: mapDbToDo)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getMappingByProductId(@PathVariable Long productId){
        Products savedProducts = productsRepository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such product found for given ID :: "+productId));

        return  productTypeRelRepository.findByProducts(savedProducts)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No Such Mapping for product found"))
                .stream()
                .map(productsTypeRelMapper :: mapDbToDo)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getMappingByProductTypeName(@PathVariable String productTypeName){
        ProductType savedProductType = productTypeRepository.findByTypeName(productTypeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Product found for the given product type name ::: "+productTypeName));

        return productTypeRelRepository.findByProductType(savedProductType)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No Such Mapping for product type found"))
                .stream()
                .map(productsTypeRelMapper :: mapDbToDo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getMappingByProductTypeId(@PathVariable Long productTypeId){
        ProductType savedProductType = productTypeRepository.findByTypeId(productTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such product found for given ID :: "+productTypeId));

        return  productTypeRelRepository.findByProductType(savedProductType)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No Such Mapping for product type found"))
                .stream()
                .map(productsTypeRelMapper :: mapDbToDo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getMappingByProductBrandName(@PathVariable String productBrandName){
        ProductBrand savedBrand = productBrandRepository.findByBrandName(productBrandName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Product found for the given product type name ::: "+productBrandName));

        return productTypeRelRepository.findByProductBrand(savedBrand)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No Such Mapping for product type found"))
                .stream()
                .map(productsTypeRelMapper :: mapDbToDo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getMappingByProductBrandId(@PathVariable Long brandId){
        ProductBrand savedBrand = productBrandRepository.findByBrandId(brandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such product found for given brand ID :: "+brandId));

        return  productTypeRelRepository.findByProductBrand(savedBrand)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No Such Mapping for product type found"))
                .stream()
                .map(productsTypeRelMapper :: mapDbToDo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getMappingByProductSubTypeName(@PathVariable String productSubTypeName){
        ProductSubType savedProductSubType = productSubTypeRepository.findBySubTypeName(productSubTypeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Product found for the given product type name ::: "+productSubTypeName));

        return productTypeRelRepository.findByProductSubType(savedProductSubType)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No Such Mapping for product type found"))
                .stream()
                .map(productsTypeRelMapper :: mapDbToDo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductTypeRelResponseDO> getMappingByProductSubTypeId(@PathVariable Long subTypeId){
        ProductSubType savedProductSubType = productSubTypeRepository.findBySubTypeId(subTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such product found for given subtype ID :: "+subTypeId));

        return  productTypeRelRepository.findByProductSubType(savedProductSubType)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No Such Mapping for product type found"))
                .stream()
                .map(productsTypeRelMapper :: mapDbToDo)
                .collect(Collectors.toList());
    }

}
