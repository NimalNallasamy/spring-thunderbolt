package com.ekart.thunderbolt.service;

import com.ekart.thunderbolt.DAO.ProductBrandDAO;
import com.ekart.thunderbolt.entity.ProductBrand;
import com.ekart.thunderbolt.mapper.ProductBrandMapper;
import com.ekart.thunderbolt.repository.ProductBrandRepository;
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
public class ProductBrandService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductBrandService.class);
    private final ProductBrandRepository productBrandRepository;
    private final ProductBrandMapper productBrandMapper;

    @Transactional(readOnly = true)
    public List<ProductBrandDAO> getAllProductBrands(){
        return productBrandRepository.findAll()
                .stream()
                .map(productBrandMapper :: mapToDO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductBrandDAO patchProducts(Long brandId, ProductBrandDAO productBrandDO){

        LOGGER.info("Updating the Product Brand details");

        ProductBrand savedProductBrand = productBrandRepository.findByBrandId(brandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ProductBrand updatedProductBrand = productBrandRepository.save(productBrandMapper.patchMap(productBrandDO, savedProductBrand));
        return productBrandMapper.mapToDO(updatedProductBrand);
    }

    @Transactional(readOnly = true)
    public ProductBrandDAO getProductBrandById(Long brandId){
        ProductBrand productBrand = productBrandRepository.findByBrandId(brandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return productBrandMapper.mapToDO(productBrand);
    }

    @Transactional(readOnly = true)
    public ProductBrandDAO getProductBrandByName(String brandName){
        ProductBrand productBrand = productBrandRepository.findByBrandName(brandName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return productBrandMapper.mapToDO(productBrand);
    }

    @Transactional
    public ProductBrandDAO saveProductBrand(ProductBrandDAO productBrandDAO){
        LOGGER.info("Inside ProductBrandService");
        LOGGER.info("Products Brand DO in Products Service ::: "+productBrandDAO.toString());
        ProductBrand savedProductBrand = productBrandRepository.save(productBrandMapper.mapToDBData(productBrandDAO));
        LOGGER.info("Saved Products Brand "+savedProductBrand);
        return productBrandMapper.mapToDO(savedProductBrand);
    }

}
