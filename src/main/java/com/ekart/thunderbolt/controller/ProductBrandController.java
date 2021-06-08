package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DAO.ProductBrandDAO;
import com.ekart.thunderbolt.service.ProductBrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/brands")
@AllArgsConstructor
@Tag(name="Product Brand" , description = "Product Brand APIs")
public class ProductBrandController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductBrandController.class);
    private final ProductBrandService productBrandService;

    @PostMapping("/brand")
    public ResponseEntity<ProductBrandDAO> createProductBrand(@RequestBody ProductBrandDAO productBrandDAO){
        return status(HttpStatus.OK).body(productBrandService.saveProductBrand(productBrandDAO));
    }

    @PatchMapping("/brand/{id}")
    public ResponseEntity<ProductBrandDAO> patchProductBrand(@PathVariable Long id, @RequestBody ProductBrandDAO productBrandDO){
        return status(HttpStatus.OK).body(productBrandService.patchProducts(id, productBrandDO));
    }

    @GetMapping
    public ResponseEntity<List<ProductBrandDAO>> getProductBrand(){
        return status(HttpStatus.OK).body(productBrandService.getAllProductBrands());
    }

    @GetMapping("/brand/id/{id}")
    public ResponseEntity<ProductBrandDAO> getProductBrandByBrandId(@PathVariable Long id){
        return status(HttpStatus.OK).body(productBrandService.getProductBrandById(id));
    }

    @GetMapping("/brand/name/{brandName}")
    public ResponseEntity<ProductBrandDAO> getProductBrandByBrandName(@PathVariable String brandName){
        return status(HttpStatus.OK).body(productBrandService.getProductBrandByName(brandName));
    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<String> deleteProductBrandById(@PathVariable Long id){
        JSONObject responseJson = new JSONObject().put("status", 200).put("message", "Product Brand Deleted Successfully");
        return status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseJson.toString());
    }

}
