package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DO.ProductsDO;
import com.ekart.thunderbolt.entity.Products;
import com.ekart.thunderbolt.service.ProductsService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);
    private final ProductsService productsService;

    @PostMapping("/product")
    public ResponseEntity<ProductsDO> createProducts(@RequestBody ProductsDO productsDO){
        LOGGER.info("Inside Create Product Details ::: "+productsDO.toString());
        ProductsDO savedProductsDo = productsService.saveProduct(productsDO);
        return status(HttpStatus.CREATED).body(savedProductsDo);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<ProductsDO> updateProducts(@PathVariable Long id, @RequestBody ProductsDO productsDO){
        LOGGER.info("Inside Update Products Details ::: "+productsDO.toString());
        ProductsDO savedProductsDo = productsService.patchProducts(id, productsDO);
        return status(HttpStatus.OK).body(savedProductsDo);
    }

    @GetMapping
    public ResponseEntity<List<ProductsDO>> getAllProductDetails(){
        LOGGER.info("Inside Get All Product Details");
//        productsService.getProducts().
        List<ProductsDO> productsDO = productsService.getProducts();
        LOGGER.info("Logger of Products Fetching123 :::: "+productsDO.toString());

        return status(HttpStatus.OK).body(productsDO);
    }

    @GetMapping("/product/id/{id}")
    public ResponseEntity<ProductsDO> getProductById(@PathVariable Long id){
        LOGGER.info("Inside Products Controller Fetching the details by product ID ::: "+id);
        return status(HttpStatus.OK).body(productsService.getProductById(id));
    }

    @GetMapping("/product/name/{name}")
    public ResponseEntity<ProductsDO> getProductById(@PathVariable String name){
        LOGGER.info("Inside Products Controller Fetching the details by product ID ::: "+name);
        return status(HttpStatus.OK).body(productsService.getProductByName(name));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){

        LOGGER.info("Inside Products Controller, Deleting the products by Product ID : "+id);
        productsService.deleteProductsById(id);
        JSONObject responseJson = new JSONObject().put("message" , "Product Deleted Successfully. ");
        LOGGER.info("Inside Products Controller, responseJson : "+responseJson);
        return status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseJson.toString());
    }

}
