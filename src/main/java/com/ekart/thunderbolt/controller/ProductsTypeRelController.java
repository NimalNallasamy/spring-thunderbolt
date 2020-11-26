package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DO.ProductTypeRelDO;
import com.ekart.thunderbolt.DO.ProductTypeRelResponseDO;
import com.ekart.thunderbolt.entity.ProductTypeRel;
import com.ekart.thunderbolt.service.ProductTypeRelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/productMappings")
@AllArgsConstructor
@Tag(name="Product Mapping" , description = "Product Mapping APIs")
public class ProductsTypeRelController {

    private final ProductTypeRelService productTypeRelService;

    @GetMapping
    public ResponseEntity<List<ProductTypeRelResponseDO>> getAllMappings(){
        return status(HttpStatus.OK).body(productTypeRelService.getAllMappings());
    }

    @GetMapping("/mapping/{id}")
    public ResponseEntity<ProductTypeRelResponseDO> getMappingById(@PathVariable Long id){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByRelationId(id));
    }

    @PostMapping("/mapping")
    public ResponseEntity<ProductTypeRelResponseDO> addNewMapping(@RequestBody ProductTypeRelDO productTypeRelDO){
        return status(HttpStatus.OK).body(productTypeRelService.addNewMapping(productTypeRelDO));
    }

    @PatchMapping("/mapping/{id}")
    public ResponseEntity<ProductTypeRel> patchMappings(@PathVariable Long id, @RequestBody ProductTypeRelDO productTypeRelDO){
        return status(HttpStatus.OK).body(productTypeRelService.patchMappings(id, productTypeRelDO));
    }

    @DeleteMapping("/mapping/{id}")
    public ResponseEntity<JSONObject> deleteMappings(@PathVariable Long id){
        JSONObject responseJson = new JSONObject();
        productTypeRelService.deleteMappings(id);
        responseJson.put("message" , "Product Type Relations Deleted Successfully.");
        responseJson.put("status","200");
        return status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseJson);
    }

    @GetMapping("/mapping/product/name/{productName}")
    public ResponseEntity<List<ProductTypeRelResponseDO>> getMappingByProductName(@PathVariable String productName){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByProductName(productName));
    }

    @GetMapping("/mapping/product/id/{id}")
    public ResponseEntity<List<ProductTypeRelResponseDO>> getMappingByProductId(@PathVariable Long id){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByProductId(id));
    }

    @GetMapping("/mapping/type/name/{productTypeName}")
    public ResponseEntity<List<ProductTypeRelResponseDO>> getMappingByProductTypeName(@PathVariable String productTypeName){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByProductTypeName(productTypeName));
    }

    @GetMapping("/mapping/type/id/{id}")
    public ResponseEntity<List<ProductTypeRelResponseDO>> getMappingByProductTypeId(@PathVariable Long id){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByProductTypeId(id));
    }

    @GetMapping("/mapping/brand/name/{productBrandName}")
    public ResponseEntity<List<ProductTypeRelResponseDO>> getMappingByProductBrandName(@PathVariable String productBrandName){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByProductBrandName(productBrandName));
    }

    @GetMapping("/mapping/brand/id/{id}")
    public ResponseEntity<List<ProductTypeRelResponseDO>> getMappingByProductBrandId(@PathVariable Long id){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByProductBrandId(id));
    }

    @GetMapping("/mapping/subType/name/{productSubTypeName}")
    public ResponseEntity<List<ProductTypeRelResponseDO>> getMappingByProductSubTypeName(@PathVariable String productSubTypeName){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByProductSubTypeName(productSubTypeName));
    }

    @GetMapping("/mapping/subType/id/{id}")
    public ResponseEntity<List<ProductTypeRelResponseDO>> getMappingByProductSubTypeId(@PathVariable Long id){
        return status(HttpStatus.OK).body(productTypeRelService.getMappingByProductSubTypeId(id));
    }

}
