package com.ekart.thunderbolt.controller;

import com.ekart.thunderbolt.DO.ProductTypeRelDO;
import com.ekart.thunderbolt.DO.ProductTypeRelResponseDO;
import com.ekart.thunderbolt.service.ProductTypeRelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/productMappings")
@AllArgsConstructor
public class ProductsTypeRelController {

    private final ProductTypeRelService productTypeRelService;

    @GetMapping
    public ResponseEntity<List<ProductTypeRelResponseDO>> getAllMappings(){
        return status(HttpStatus.OK).body(productTypeRelService.getAllMappings());
    }

    @PostMapping("/mapping")
    public ResponseEntity<ProductTypeRelResponseDO> addNewMapping(@RequestBody ProductTypeRelDO productTypeRelDO){
        return status(HttpStatus.OK).body(productTypeRelService.addNewMapping(productTypeRelDO));
    }

    @PatchMapping("/mapping/{id}")
    public void patchMappings(){

    }

    @DeleteMapping("/mapping/{id}")
    public void deleteMappings(@PathVariable Long id){

    }

    @GetMapping("/mapping/product/{productName}")
    public void getMappingByProductName(@PathVariable String productName){

    }

    @GetMapping("/mapping/product/{id}")
    public void getMappingByProductId(@PathVariable Long id){

    }

    @GetMapping("/mapping/type/{productTypeName}")
    public void getMappingByProductTypeName(@PathVariable String productTypeName){

    }

    @GetMapping("/mapping/type/{id}")
    public void getMappingByProductTypeId(@PathVariable Long id){

    }

    @GetMapping("/mapping/brand/{productBrandName}")
    public void getMappingByProductBrandName(@PathVariable String productBrandName){

    }

    @GetMapping("/mapping/brand/{id}")
    public void getMappingByProductBrandId(@PathVariable Long id){

    }

    @GetMapping("/mapping/subType/{productSubTypeName}")
    public void getMappingByProductSubTypeName(@PathVariable String productSubTypeName){

    }

    @GetMapping("/mapping/subType/{id}")
    public void getMappingByProductSubTypeId(@PathVariable Long id){

    }

}
