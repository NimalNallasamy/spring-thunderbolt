package com.ekart.thunderbolt.service;

import com.ekart.thunderbolt.DO.ProductSubTypeDO;
import com.ekart.thunderbolt.entity.ProductSubType;
import com.ekart.thunderbolt.mapper.ProductSubTypeMapper;
import com.ekart.thunderbolt.repository.ProductSubTypeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ProductSubTypeService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductSubType.class);
    private final ProductSubTypeRepository productSubTypeRepository;
    private final ProductSubTypeMapper productSubTypeMapper;

    @Transactional
    public ProductSubTypeDO saveProductSubType(ProductSubTypeDO productSubTypeDO){
        ProductSubType savedProductSubType = productSubTypeRepository.save(productSubTypeMapper.mapDoToDb(productSubTypeDO));
        return productSubTypeMapper.mapDbToDo(savedProductSubType);
    }

    @Transactional
    public ProductSubTypeDO patchProductSubType(Long subTypeId, ProductSubTypeDO productSubTypeDO){
        ProductSubType savedProductSubType = productSubTypeRepository.findBySubTypeId(subTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ProductSubType updatedProductSubType = productSubTypeRepository.save(productSubTypeMapper.patchMap(productSubTypeDO, savedProductSubType));
        return productSubTypeMapper.mapDbToDo(updatedProductSubType);
    }

    @Transactional(readOnly = true)
    public List<ProductSubTypeDO> getAllProductsSubType(){
        return productSubTypeRepository.findAll()
                .stream()
                .map(productSubTypeMapper :: mapDbToDo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductSubTypeDO getProductSubTypeById(Long subTypeId){
        ProductSubType savedProductSubType = productSubTypeRepository.findById(subTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return productSubTypeMapper.mapDbToDo(savedProductSubType);
    }

    @Transactional(readOnly = true)
    public ProductSubTypeDO getProductSubTypeByName(String subTypeName){
        ProductSubType savedProductSubType = productSubTypeRepository.findBySubTypeName(subTypeName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return productSubTypeMapper.mapDbToDo(savedProductSubType);
    }

    @Transactional
    public void deleteProductSubTypeById(Long subTypeId){
        ProductSubType savedProductSubType = productSubTypeRepository.findById(subTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        productSubTypeRepository.delete(savedProductSubType);
    }

}
