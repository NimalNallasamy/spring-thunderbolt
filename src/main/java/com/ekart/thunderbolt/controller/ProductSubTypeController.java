package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DO.ProductSubTypeDO;
import com.ekart.thunderbolt.service.ProductSubTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/subTypes")
@AllArgsConstructor
@Tag(name="Product Sub Type" , description = "Product Sub Type APIs")
public class ProductSubTypeController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductSubTypeController.class);
    private final ProductSubTypeService productSubTypeService;

    @PostMapping("/subType")
    public ResponseEntity<ProductSubTypeDO> createProductType(@RequestBody ProductSubTypeDO productSubTypeDO){
        return status(HttpStatus.OK).body(productSubTypeService.saveProductSubType(productSubTypeDO));
    }

    @PatchMapping("/subType/{id}")
    public ResponseEntity<ProductSubTypeDO> patchProductTypeById(@PathVariable Long id, @RequestBody ProductSubTypeDO productSubTypeDO){
        return status(HttpStatus.OK).body(productSubTypeService.patchProductSubType(id, productSubTypeDO));
    }

    @GetMapping
    public ResponseEntity<List<ProductSubTypeDO>> getAllProductSubTypes(){
        return status(HttpStatus.OK).body(productSubTypeService.getAllProductsSubType());
    }

    @GetMapping("/subType/id/{id}")
    public ResponseEntity<ProductSubTypeDO> getProductSubTypeById(@PathVariable Long id){
        return status(HttpStatus.OK).body(productSubTypeService.getProductSubTypeById(id));
    }

    @GetMapping("/subType/name/{subTypeName}")
    public ResponseEntity<ProductSubTypeDO> getProductSubTypeByName(@PathVariable String subTypeName){
        return status(HttpStatus.OK).body(productSubTypeService.getProductSubTypeByName(subTypeName));
    }

    @DeleteMapping("/subType/{id}")
    public ResponseEntity<String> deleteProductSubTypeById(@PathVariable Long id){
        JSONObject responseJson = new JSONObject().put("status", 200).put("message", "Product Sub Type Deleted Successfully");
        productSubTypeService.deleteProductSubTypeById(id);
        return status(HttpStatus.OK).body(responseJson.toString());
    }

}
