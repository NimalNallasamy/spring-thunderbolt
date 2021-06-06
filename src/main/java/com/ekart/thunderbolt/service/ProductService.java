package com.ekart.thunderbolt.service;

import com.ekart.thunderbolt.DAO.ProductDAO;
import com.ekart.thunderbolt.entity.Product;
import com.ekart.thunderbolt.mapper.ProductMapper;
import com.ekart.thunderbolt.repository.ProductRepository;
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
public class ProductService {

    private ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Transactional
    public ProductDAO createProduct(ProductDAO productDAO){
        Product createdProduct = productRepository.save(productMapper.mapToProduct(productDAO));
        return productMapper.mapToProductDAO(createdProduct);
    }

    @Transactional
    public ProductDAO patchProductData(Long productId, ProductDAO productDAO){

        Product productToBePatched = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Product patchedProduct = productRepository.save(productMapper.patchDO(productDAO, productToBePatched));
        return productMapper.mapToProductDAO(patchedProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductDAO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper ::mapToProductDAO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDAO getProductById(Long productId){
        Product productFetchedById = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return productMapper.mapToProductDAO(productFetchedById);
    }

    @Transactional(readOnly = true)
    public ProductDAO getProductByName(String productName){
        Product productFetchedById = productRepository.findByProductName(productName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return productMapper.mapToProductDAO(productFetchedById);
    }

    @Transactional
    public void deleteProductById(Long productId){

        Product productToBeDeleted = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        productRepository.delete(productToBeDeleted);
        LOGGER.info("Data Deleted successfully for the given id: "+productId);
    }

}
