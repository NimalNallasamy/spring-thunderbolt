package com.ekart.thunderbolt.service;

import com.ekart.thunderbolt.DO.ProductsDO;
import com.ekart.thunderbolt.entity.Products;
import com.ekart.thunderbolt.mapper.ProductsMapper;
import com.ekart.thunderbolt.repository.ProductBrandRepository;
import com.ekart.thunderbolt.repository.ProductSubTypeRepository;
import com.ekart.thunderbolt.repository.ProductTypeRelRepository;
import com.ekart.thunderbolt.repository.ProductsRepository;
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
public class ProductsService {

    private ProductsRepository productsRepository;
    private ProductBrandRepository productBrandRepository;
    private ProductSubTypeRepository productSubTypeRepository;
    private ProductTypeRelRepository productTypeRelRepository;

    private final ProductsMapper productsMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductsService.class);

    @Transactional
    public ProductsDO saveProduct(ProductsDO productsDO){
        LOGGER.info("Inside Saving products");
        LOGGER.info("productsDo to Products Map ::: " + productsMapper.map(productsDO).toString());
        Products savedProduct = productsRepository.save(productsMapper.map(productsDO));
        LOGGER.info("Saved Product map ::: " + savedProduct);
        return productsMapper.mapToProducts(savedProduct);
//        Products savedProduct = productsMapper.map(productsDO);
//        ProductsDO savedProductDo = productsMapper.mapToProducts(savedProduct);
//        savedProductDo.setProductId(1l);
//        return savedProductDo;
    }

    @Transactional
    public ProductsDO patchProducts(Long productId, ProductsDO productsDO){
        LOGGER.info("Inside patching products");

        Products savedProducts = productsRepository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Products updatedProduct = productsRepository.save(productsMapper.patchDO(productsDO,savedProducts));
        return productsMapper.mapToProducts(updatedProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductsDO> getProducts(){
        LOGGER.info("Inside Service ::: fetching all products");
        return productsRepository.findAll()
                .stream()
                .map(productsMapper :: mapToProducts)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public ProductsDO getProductById(Long productId){
        LOGGER.info("Inside Service ::: fetching the product by Id ::: "+productId);
        Products productById = productsRepository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return productsMapper.mapToProducts(productById);

    }

    @Transactional(readOnly = true)
    public ProductsDO getProductByName(String productName){
        LOGGER.info("Inside Service ::: fetching the product by Id ::: "+productName);
        Products productByName = productsRepository.findByProductName(productName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return productsMapper.mapToProducts(productByName);

    }

    @Transactional
    public void deleteProductsById(Long productId){
        LOGGER.info("Inside Products Service ::: Deleting the product by Product Id ::: "+productId);
        Products productToBeDeleted = productsRepository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productsRepository.delete(productToBeDeleted);
    }
}
