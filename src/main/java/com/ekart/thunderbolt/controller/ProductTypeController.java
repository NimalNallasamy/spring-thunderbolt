package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DO.ProductTypeDO;
import com.ekart.thunderbolt.service.ProductTypeService;
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
@RequestMapping("/api/types")
@AllArgsConstructor
//@NoArgsConstructor
@Tag(name="Product Type" , description = "Product Type APIs")
public class ProductTypeController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductTypeController.class);
    private final ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductTypeDO>> getAllProductTypes(){
        return status(HttpStatus.OK).body(productTypeService.getAllProductType());
    }

    @PostMapping("/type")
    public ResponseEntity<ProductTypeDO> saveProductType(@RequestBody ProductTypeDO productTypeDO){
        return status(HttpStatus.OK).body(productTypeService.saveProductType(productTypeDO));
    }

    @PatchMapping("/type/{id}")
    public ResponseEntity<ProductTypeDO> patchProductType(@PathVariable Long id, @RequestBody ProductTypeDO productTypeDO){
        return status(HttpStatus.OK).body(productTypeService.patchProductType(id, productTypeDO));
    }

    @GetMapping("/type/id/{id}")
    public ResponseEntity<ProductTypeDO> getProductTypeById(@PathVariable Long id){
        return status(HttpStatus.OK).body(productTypeService.getProductTypeById(id));
    }

    @GetMapping("/type/name/{typeName}")
    public ResponseEntity<ProductTypeDO> getProductTypeByName(@PathVariable String typeName){
        return status(HttpStatus.OK).body(productTypeService.getProductTypeByName(typeName));
    }

    @DeleteMapping("/type/{id}")
    public ResponseEntity<String> deleteProductTypeById(@PathVariable Long id){
        JSONObject responseJson = new JSONObject().put("status" , 200).put("message","Product Type deleted successfully");
        productTypeService.deleteProductTypeById(id);
        return status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseJson.toString());
    }

}
