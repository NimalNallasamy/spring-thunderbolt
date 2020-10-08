package com.ekart.thunderbolt.service;

import com.ekart.thunderbolt.DO.ProductTypeDO;
import com.ekart.thunderbolt.entity.ProductType;
import com.ekart.thunderbolt.mapper.ProductTypeMapper;
import com.ekart.thunderbolt.repository.ProductTypeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ProductTypeService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductTypeService.class);
    private final ProductTypeMapper productTypeMapper;
    private final ProductTypeRepository productTypeRepository;

    @Transactional(readOnly = true)
    public List<ProductTypeDO> getAllProductType(){
        LOGGER.info("Fetching all product types");
        return productTypeRepository.findAll()
                .stream()
                .map(productTypeMapper :: mapToDo )
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductTypeDO saveProductType(ProductTypeDO productTypeDO){
        LOGGER.info("Saving a product Type :: "+productTypeDO);
        ProductType savedProductType = productTypeRepository.save(productTypeMapper.mapToDBDO(productTypeDO));
        return productTypeMapper.mapToDo(savedProductType);
    }

    @Transactional
    public ProductTypeDO patchProductType(Long typeId, ProductTypeDO productTypeDO){
        LOGGER.info("Patching a product type ::: "+productTypeDO);
        ProductType savedProductType = productTypeRepository.findByTypeId(typeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ProductType patchedProductType = productTypeRepository.save(productTypeMapper.patchMap(productTypeDO, savedProductType));
        return productTypeMapper.mapToDo(patchedProductType);
    }

    @Transactional(readOnly = true)
    public ProductTypeDO getProductTypeById(Long typeId){
        LOGGER.info("Fetching the Product Type by ID :: "+typeId);
        ProductType savedProductType = productTypeRepository.findByTypeId(typeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return productTypeMapper.mapToDo(savedProductType);
    }

    @Transactional(readOnly = true)
    public ProductTypeDO getProductTypeByName(String typeName){
        LOGGER.info("Fetching the Product Type by Name :: "+typeName);
        ProductType savedProductType = productTypeRepository.findByTypeName(typeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return productTypeMapper.mapToDo(savedProductType);
    }

    @Transactional
    public void deleteProductTypeById(Long typeId){
        LOGGER.info("Deleting the product type by ID : "+typeId);
        ProductType savedProductType = productTypeRepository.findByTypeId(typeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        productTypeRepository.delete(savedProductType);
    }

}
